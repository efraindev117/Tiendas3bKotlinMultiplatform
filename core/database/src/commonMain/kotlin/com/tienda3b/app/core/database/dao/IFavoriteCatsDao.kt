package com.tienda3b.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import com.tienda3b.app.core.database.model.favorite.CatWithFavorite
import com.tienda3b.app.core.database.model.favorite.FavoriteCatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IFavoriteCatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(entity: FavoriteCatEntity)

    @Query("DELETE FROM favorite_cats WHERE cat_id = :catId")
    suspend fun removeFavorite(catId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cats WHERE cat_id = :catId)")
    suspend fun isFavorite(catId: String): Boolean

    @Query("""
        SELECT * FROM favorite_cats
        ORDER BY created_at DESC, name COLLATE NOCASE ASC
    """)
    fun observeFavorites(): Flow<List<FavoriteCatEntity>>

    @Transaction
    suspend fun toggleFavorite(catId: String, name: String, imageUrl: String?) {
        if (isFavorite(catId)) {
            removeFavorite(catId)
        } else {
            addFavorite(FavoriteCatEntity(catId = catId, name = name, imageUrl = imageUrl))
        }
    }
}