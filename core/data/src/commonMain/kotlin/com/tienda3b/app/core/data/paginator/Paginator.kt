package com.tienda3b.app.core.data.paginator

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Paginator<Key, Item>(
    initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<Item>,
    private val getNextKey: suspend (currentKey: Key, result: Item) -> Key,
    private val onError: suspend (Throwable?) -> Unit,
    private val onSuccess: suspend (result: Item, newKey: Key) -> Unit,
    private val isEnd: (currentKey: Key, result: Item) -> Boolean
) {
    private val lock = Mutex()
    private var currentKey: Key = initialKey
    private var endReached: Boolean = false

    suspend fun loadNextItems() = lock.withLock {
        if (endReached) return
        onLoadUpdated(true)

        val result = runCatching { onRequest(currentKey) }.getOrElse { Result.failure(it) }
        val item = result.getOrElse {
            onLoadUpdated(false)
            onError(it)
            return
        }

        // Evaluamos fin ANTES de avanzar la llave
        endReached = isEnd(currentKey, item)

        val newKey = getNextKey(currentKey, item)
        currentKey = newKey

        onSuccess(item, newKey)
        onLoadUpdated(false)
    }

    suspend fun refresh(toKey: Key = currentKey) = lock.withLock {
        endReached = false
        currentKey = toKey
    }

    suspend fun reset(toKey: Key) = lock.withLock {
        endReached = false
        currentKey = toKey
    }
}