package com.shared.di

import com.tienda3b.app.core.data.di.appModule
import com.tienda3b.app.core.domain.di.domainModule
import com.tienda3b.feature.auth.authViewModelModule
import com.tienda3b.feature.list_detail.catsViewModelModule

object KoinModules {
    val modulesList = listOf(
        appModule,
        domainModule,
        catsViewModelModule,
        authViewModelModule
    )
}