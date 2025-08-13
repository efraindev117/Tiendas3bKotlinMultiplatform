package com.tienda3b.app.core.domain.usescase.favorites

import com.tienda3b.app.core.data.repository.IFavorites

class RemoveFavoriteUseCase(private val repo: IFavorites) {
    suspend operator fun invoke(catId: String) = repo.remove(catId)
}
