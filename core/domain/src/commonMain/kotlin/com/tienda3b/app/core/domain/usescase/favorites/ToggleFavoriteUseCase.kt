package com.tienda3b.app.core.domain.usescase.favorites

import com.tienda3b.app.core.data.repository.IFavorites

class ToggleFavoriteUseCase(private val repo: IFavorites) {
    suspend operator fun invoke(catId: String, name: String, imageUrl: String?) {
        repo.toggle(catId, name, imageUrl)
    }
}
