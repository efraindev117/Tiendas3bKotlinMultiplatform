package com.tienda3b.app.core.domain.usescase.favorites

import com.tienda3b.app.core.data.repository.IFavorites

class AddFavoriteUseCase(private val repo: IFavorites) {
    suspend operator fun invoke(catId: String, name: String, imageUrl: String?) {
        repo.add(catId, name, imageUrl)
    }
}