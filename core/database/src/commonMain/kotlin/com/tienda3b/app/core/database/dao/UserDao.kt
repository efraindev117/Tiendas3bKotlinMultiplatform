package com.tienda3b.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tienda3b.app.core.database.model.auth.SessionEntity
import com.tienda3b.app.core.database.model.auth.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): UserEntity?

    @Query("UPDATE users SET passwordHash = NULL WHERE email = :email")
    suspend fun deletePassword(email: String): Int

    @Query("DELETE FROM users")
    suspend fun deleteAll(): Int
}

