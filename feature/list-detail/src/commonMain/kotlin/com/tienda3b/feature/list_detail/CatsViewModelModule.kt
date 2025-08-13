package com.tienda3b.feature.list_detail

import com.tienda3b.app.core.common.di.AppDispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val catsViewModelModule = module {
    viewModel {
        CatsViewModel(
            catsUsesCase = get(),
            favoritesUsesCase = get(),
            dispatcher = get(named(AppDispatchers.Default.name))
        )
    }
}