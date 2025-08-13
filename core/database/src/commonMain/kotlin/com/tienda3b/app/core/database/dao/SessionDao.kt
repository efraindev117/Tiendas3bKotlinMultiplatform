package com.tienda3b.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tienda3b.app.core.database.model.auth.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * FROM session WHERE singleton = 0 LIMIT 1")
    fun observeSession(): Flow<SessionEntity?>

    @Query("SELECT * FROM session WHERE singleton = 0 LIMIT 1")
    suspend fun getSession(): SessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(session: SessionEntity)

    @Query("DELETE FROM session")
    suspend fun clear()
}