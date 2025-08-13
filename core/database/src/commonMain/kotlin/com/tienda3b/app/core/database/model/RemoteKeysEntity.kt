package com.tienda3b.app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val label: String = "cats",
    val nextPage: Int?,
    val pageSize: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
