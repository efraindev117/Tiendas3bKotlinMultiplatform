package com.tienda3b.app.core.data.repository

import com.tienda3b.app.core.data.model.cats.toEntities
import com.tienda3b.app.core.data.paginator.Paginator
import com.tienda3b.app.core.database.dao.ICatsDao
import com.tienda3b.app.core.database.dao.IRemoteKeysDao
import com.tienda3b.app.core.database.model.RemoteKeysEntity
import com.tienda3b.app.core.database.model.cats.toUi
import com.tienda3b.app.core.model.CatsUiModelItem
import com.tienda3b.app.core.network.model.NetworkCatsItemModel
import com.tienda3b.app.core.network.repository.INetworkCatsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatsRepositoryImpl(
    private val network: INetworkCatsDataSource,
    private val catsDao: ICatsDao,
    private val keysDao: IRemoteKeysDao,
    private val pageSize: Int = 20
) : ICatsRepository {
    private lateinit var paginator: Paginator<Int, List<NetworkCatsItemModel>>

    override fun getListCats(): Flow<List<CatsUiModelItem>> =
        catsDao.observeAll().map { entities -> entities.map { it.toUi() } }

    override suspend fun loadNextCats() {
        val keys = keysDao.get() ?: seedKeysFromLocalCount()
        val next = keys.nextPage ?: return // fin alcanzado: no hacemos request
        val p = buildPaginator(initialKey = next)
        paginator = p
        p.loadNextItems()
    }

    override suspend fun refreshCats() {
        catsDao.deleteAll()
        keysDao.upsert(RemoteKeysEntity(nextPage = 0, pageSize = pageSize))
        paginator = buildPaginator(initialKey = 0)
        paginator!!.loadNextItems()
    }

    private fun buildPaginator(initialKey: Int) =
        Paginator(
            initialKey = initialKey,
            onLoadUpdated = { loading ->
                println("CatsRepo onLoadUpdated=$loading")
            },
            onRequest = { page ->
                println("CatsRepo onRequest page=$page limit=$pageSize")
                runCatching { network.getCatList(limit = pageSize, page = page) }
                    .also { res ->
                        res.onSuccess { println("CatsRepo onRequest OK count=${it.size}") }
                            .onFailure { println("CatsRepo onRequest FAIL page=$page") }
                    }
            },
            getNextKey = { currentKey, result ->
                val next = if (result.isEmpty()) currentKey else currentKey + 1
                println("CatsRepo getNextKey current=$currentKey result.size=${result.size} -> next=$next")
                next
            },
            onError = { t ->
                println("CatsRepo Paginator error $t")
            },
            onSuccess = { catsFromNetwork, newKey ->
                val entities = catsFromNetwork.toEntities()
                println("CatsRepo onSuccess saving entities=${entities.size} newKey=$newKey")
                catsDao.upsertCats(entities)

                val reachedEnd = catsFromNetwork.size < pageSize
                val next = if (reachedEnd) null else newKey
                println("CatsRepo onSuccess reachedEnd=$reachedEnd -> nextPage=$next")

                keysDao.upsert(
                    RemoteKeysEntity(
                        nextPage = next,
                        pageSize = pageSize,
                        lastUpdated = System.currentTimeMillis()
                    )
                )
            },
            isEnd = { _, result ->
                val end = result.size < pageSize || result.isEmpty()
                println("CatsRepo isEnd? result.size=${result.size} pageSize=$pageSize -> $end")
                end
            },
        )

    override fun getCatById(id: String): Flow<CatsUiModelItem?> {
        return catsDao.observeById(id).map { it?.toUi() }
    }

    private suspend fun seedKeysFromLocalCount(): RemoteKeysEntity {
        val count = catsDao.count()
        val next = (count / pageSize).toInt()
        val seeded = RemoteKeysEntity(nextPage = next, pageSize = pageSize)
        keysDao.upsert(seeded)
        println("")
        return seeded
    }
}