package com.tienda3b.app.core.model.favorite

import com.tienda3b.app.core.model.CatsUiModelItem

data class FavoriteUi(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val createdAt: Long
)

data class CatUi(
    val item: CatsUiModelItem,
    val isFavorite: Boolean
)



