package com.tienda3b.app.core.data.repository

import com.tienda3b.app.core.model.CatsUiModelItem
import kotlinx.coroutines.flow.Flow

interface ICatsRepository {
    fun getListCats(): Flow<List<CatsUiModelItem>>
    suspend fun loadNextCats()
    suspend fun refreshCats()
    fun getCatById(id: String): Flow<CatsUiModelItem?>
}