package com.tienda3b.app.core.domain.usescase.auth

import com.tienda3b.app.core.data.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class ObserveAuthStateUseCase(private val repo: IAuthRepository) {
    operator fun invoke(): Flow<Boolean> = repo.observeIsLoggedIn()
}
