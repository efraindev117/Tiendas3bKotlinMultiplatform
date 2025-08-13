package com.tienda3b.app.core.data.repository

import com.tienda3b.app.core.database.dao.SessionDao
import com.tienda3b.app.core.database.dao.UserDao
import com.tienda3b.app.core.database.model.auth.SessionEntity
import com.tienda3b.app.core.database.model.auth.UserEntity
import com.tienda3b.app.core.database.model.auth.toUi
import com.tienda3b.app.core.model.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.security.MessageDigest
import java.util.UUID

class AuthRepositoryImpl(
    private val userDao: UserDao,
    private val sessionDao: SessionDao
) : IAuthRepository {

    override suspend fun signUp(name: String, email: String, password: String): AuthResult<User> {
        if (userDao.findByEmail(email) != null) return AuthResult.Error("El correo ya existe")
        val entity = UserEntity(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            passwordHash = hash(password)
        )
        userDao.insert(entity)
        return AuthResult.Success(entity.toUi())
    }

    override suspend fun login(email: String, password: String): AuthResult<User> {
        val user = userDao.findByEmail(email) ?: return AuthResult.Error("Usuario no existe")
        if (user.passwordHash.isBlank()) return AuthResult.Error("El usuario no tiene contraseña (usa 'Olvidé mi contraseña')")
        if (user.passwordHash != hash(password)) return AuthResult.Error("Credenciales inválidas")

        sessionDao.upsert(
            SessionEntity(
                userId = user.id,
                email = user.email,
                startedAt = System.currentTimeMillis()
            )
        )
        return AuthResult.Success(user.toUi())
    }
    override suspend fun resetPassword(email: String): AuthResult<Unit> {
        val rows = userDao.deletePassword(email)
        if (rows == 0) return AuthResult.Error("Usuario no existe")
        sessionDao.clear()
        return AuthResult.Success(Unit)
    }

    override suspend fun logout(): AuthResult<Unit> {
        sessionDao.clear()
        return AuthResult.Success(Unit)
    }

    private fun hash(s: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return md.digest(s.toByteArray()).joinToString("") { "%02x".format(it) }
    }

    override suspend fun deleteAllAccounts(): AuthResult<Unit> {
        val deleted = userDao.deleteAll()
        sessionDao.clear()
        return if (deleted > 0) {
            AuthResult.Success(Unit)
        } else {
            AuthResult.Error("No hay cuentas registradas")
        }
    }

    override fun observeIsLoggedIn(): Flow<Boolean> {
       return sessionDao.observeSession().map { it?.userId?.isNotBlank() == true }
    }

    override suspend fun isLoggedIn(): Boolean {
       return sessionDao.getSession()?.userId?.isNotBlank() == true
    }
}