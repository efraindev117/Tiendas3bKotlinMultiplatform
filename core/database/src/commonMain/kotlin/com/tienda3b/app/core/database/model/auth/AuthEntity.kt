package com.tienda3b.app.core.database.model.auth

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tienda3b.app.core.model.auth.User

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val passwordHash: String
)

fun UserEntity.toUi(): User =
    User(
        id = id,
        name = name,
        email = email
    )

@Entity(tableName = "session")
data class SessionEntity(
    @PrimaryKey val singleton: Int = 0,
    val userId: String?,
    val email: String?,
    val startedAt: Long
)