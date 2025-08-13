package com.tienda3b.app.core.domain.usescase.favorites

import com.tienda3b.app.core.data.repository.IFavorites
import com.tienda3b.app.core.model.favorite.CatUi
import com.tienda3b.app.core.model.favorite.FavoriteUi
import kotlinx.coroutines.flow.Flow

class ObserveFavoriteIdsUseCase(private val repo: IFavorites) {
    operator fun invoke(): Flow<Set<String>> =
        repo.observeFavoriteIds()
}

class ObserveFavoritesUseCase(private val repo: IFavorites) {
    operator fun invoke(): Flow<List<FavoriteUi>> = repo.observeFavorites()
}
