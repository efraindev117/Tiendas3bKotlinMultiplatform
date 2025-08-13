package com.tienda3b.app.core.data.repository

import com.tienda3b.app.core.model.auth.User
import kotlinx.coroutines.flow.Flow

sealed class AuthResult<out T> {
    data class Success<T>(val data: T): AuthResult<T>()
    data class Error(val message: String): AuthResult<Nothing>()
}

interface IAuthRepository {
    suspend fun signUp(name: String, email: String, password: String): AuthResult<User>
    suspend fun login(email: String, password: String): AuthResult<User>
    suspend fun resetPassword(email: String): AuthResult<Unit>
    suspend fun logout(): AuthResult<Unit>
    suspend fun deleteAllAccounts(): AuthResult<Unit>

    fun observeIsLoggedIn(): Flow<Boolean>
    suspend fun isLoggedIn(): Boolean


}

