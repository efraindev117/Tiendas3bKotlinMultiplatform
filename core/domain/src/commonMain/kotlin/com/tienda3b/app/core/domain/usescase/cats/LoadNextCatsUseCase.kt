package com.tienda3b.app.core.domain.usescase.cats

import com.tienda3b.app.core.data.repository.ICatsRepository

class LoadNextCatsUseCase(
    private val repo: ICatsRepository
) {
    suspend operator fun invoke() = repo.loadNextCats()
}
