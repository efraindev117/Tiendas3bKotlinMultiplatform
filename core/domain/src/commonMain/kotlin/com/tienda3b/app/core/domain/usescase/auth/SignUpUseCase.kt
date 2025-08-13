package com.tienda3b.app.core.domain.usescase.auth

import com.tienda3b.app.core.data.repository.AuthResult
import com.tienda3b.app.core.data.repository.IAuthRepository
import com.tienda3b.app.core.model.auth.User

class SignUpUseCase(
    private val repo: IAuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): AuthResult<User> {
        return repo.signUp(name, email, password)
    }
}
