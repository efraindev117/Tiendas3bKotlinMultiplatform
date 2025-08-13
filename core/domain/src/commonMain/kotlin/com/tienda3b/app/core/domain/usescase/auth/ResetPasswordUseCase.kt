package com.tienda3b.app.core.domain.usescase.auth

import com.tienda3b.app.core.data.repository.AuthResult
import com.tienda3b.app.core.data.repository.IAuthRepository

class ResetPasswordUseCase(
    private val repo: IAuthRepository
) {
    suspend operator fun invoke(email: String): AuthResult<Unit> {
        return repo.resetPassword(email)
    }
}
