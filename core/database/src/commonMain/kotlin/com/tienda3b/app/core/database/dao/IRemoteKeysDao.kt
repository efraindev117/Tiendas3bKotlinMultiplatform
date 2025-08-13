package com.tienda3b.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tienda3b.app.core.database.model.RemoteKeysEntity

@Dao
interface IRemoteKeysDao {
    @Query("SELECT * FROM remote_keys WHERE label = :label LIMIT 1")
    suspend fun get(label: String = "cats"): RemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(keys: RemoteKeysEntity)

    @Query("DELETE FROM remote_keys WHERE label = :label")
    suspend fun clear(label: String = "cats")
}