package com.tienda3b.app.core.data.model

import com.tienda3b.app.core.database.model.cats.toUi
import com.tienda3b.app.core.database.model.favorite.CatWithFavorite
import com.tienda3b.app.core.database.model.favorite.FavoriteCatEntity
import com.tienda3b.app.core.model.favorite.CatUi
import com.tienda3b.app.core.model.favorite.FavoriteUi

fun FavoriteUi.toEntity(): FavoriteCatEntity =
    FavoriteCatEntity(
        catId = id,
        name = name,
        imageUrl = imageUrl,
        createdAt = createdAt
    )

fun FavoriteCatEntity.toUi(): FavoriteUi =
    FavoriteUi(
        id = catId,
        name = name,
        imageUrl = imageUrl,
        createdAt = createdAt
    )

fun CatWithFavorite.toUi(): CatUi =
    CatUi(
        item = cat.toUi(),
        isFavorite = isFavorite
    )