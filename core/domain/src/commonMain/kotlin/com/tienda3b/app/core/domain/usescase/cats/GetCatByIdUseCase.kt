package com.tienda3b.app.core.domain.usescase.cats

import com.tienda3b.app.core.data.repository.ICatsRepository
import com.tienda3b.app.core.model.CatsUiModelItem
import kotlinx.coroutines.flow.Flow

class GetCatByIdUseCase(private val repo: ICatsRepository) {
    operator fun invoke(id: String): Flow<CatsUiModelItem?> = repo.getCatById(id)
}
