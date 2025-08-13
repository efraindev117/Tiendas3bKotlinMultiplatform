package com.tienda3b.app.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Upsert
import com.tienda3b.app.core.database.model.cats.CatsEntity
import com.tienda3b.app.core.database.model.favorite.CatWithFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface ICatsDao {
    @Upsert
    suspend fun upsertCats(cats: List<CatsEntity>)

    @Query("SELECT * FROM cats ORDER BY name COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<CatsEntity>>

    @Query("SELECT * FROM cats WHERE id = :id LIMIT 1")
    fun observeById(id: String): Flow<CatsEntity?>

    @Query("DELETE FROM cats")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM cats")
    suspend fun count(): Long

    @Query("SELECT COUNT(*) FROM cats WHERE id IN (:ids)")
    suspend fun countByIds(ids: List<String>): Int
}