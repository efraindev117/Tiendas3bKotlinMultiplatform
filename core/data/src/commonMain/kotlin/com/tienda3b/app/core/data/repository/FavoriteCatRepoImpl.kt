package com.tienda3b.app.core.data.repository

import com.tienda3b.app.core.database.dao.IFavoriteCatsDao
import com.tienda3b.app.core.database.model.favorite.FavoriteCatEntity
import com.tienda3b.app.core.database.model.favorite.toUi
import com.tienda3b.app.core.model.favorite.FavoriteUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FavoriteCatRepoImpl(
    private val dao: IFavoriteCatsDao,
    private val io: CoroutineDispatcher
) : IFavorites {
    override suspend fun add(catId: String, name: String, imageUrl: String?) = withContext(io) {
        dao.addFavorite(FavoriteCatEntity(catId = catId, name = name, imageUrl = imageUrl))
    }

    override suspend fun remove(catId: String) = withContext(io) {
        dao.removeFavorite(catId)
    }

    override suspend fun toggle(catId: String, name: String, imageUrl: String?) = withContext(io) {
        dao.toggleFavorite(catId, name, imageUrl)
    }

    override fun observeFavorites(): Flow<List<FavoriteUi>> =
        dao.observeFavorites().map { list -> list.map { it.toUi() } }

    override fun observeFavoriteIds(): Flow<Set<String>> =
        dao.observeFavorites().map { list -> list.map { it.catId }.toSet() }
}