package com.tienda3b.app.core.database.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.tienda3b.app.core.database.model.cats.CatsEntity
import com.tienda3b.app.core.model.favorite.FavoriteUi

@Entity(
    tableName = "favorite_cats",
    primaryKeys = ["cat_id"],
    indices = [Index(value = ["cat_id"], unique = true)]
)
data class FavoriteCatEntity(
    @ColumnInfo(name = "cat_id") val catId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)

data class CatWithFavorite(
    @Embedded val cat: CatsEntity,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)

fun FavoriteCatEntity.toUi() = FavoriteUi(
    id = catId, name = name, imageUrl = imageUrl, createdAt = createdAt
)
