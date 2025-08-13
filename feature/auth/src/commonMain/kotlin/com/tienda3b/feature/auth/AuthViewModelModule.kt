package com.tienda3b.feature.auth

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authViewModelModule = module {
    viewModel {
        AuthViewModel(
            auth = get()
        )
    }
}