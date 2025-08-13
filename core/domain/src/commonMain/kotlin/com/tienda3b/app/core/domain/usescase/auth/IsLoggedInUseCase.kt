package com.tienda3b.app.core.domain.usescase.auth

import com.tienda3b.app.core.data.repository.IAuthRepository

class IsLoggedInUseCase(private val repo: IAuthRepository) {
    suspend operator fun invoke(): Boolean = repo.isLoggedIn()
}
