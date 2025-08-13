package com.tienda3b.app.core.data.repository

import com.tienda3b.app.core.model.favorite.CatUi
import com.tienda3b.app.core.model.favorite.FavoriteUi
import kotlinx.coroutines.flow.Flow

interface IFavorites {
    suspend fun add(catId: String, name: String, imageUrl: String?)
    suspend fun remove(catId: String)
    fun observeFavoriteIds(): Flow<Set<String>>
    // opcional (muy útil)
    suspend fun toggle(catId: String, name: String, imageUrl: String?)
    fun observeFavorites(): Flow<List<FavoriteUi>>
}