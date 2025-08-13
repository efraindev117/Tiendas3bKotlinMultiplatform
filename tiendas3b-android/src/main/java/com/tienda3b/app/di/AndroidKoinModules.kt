package com.tienda3b.app.di

import com.shared.di.KoinModules
import com.tienda3b.app.core.data.di.appModule
import com.tienda3b.app.core.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object AndroidKoinModules {
    val androidModules = listOf(
        appModule,
        domainModule
    )
}

fun initKoinAndroid(
    config: KoinAppDeclaration? = null
) {
    startKoin {
        config?.invoke(this)
        modules(KoinModules.modulesList + AndroidKoinModules.androidModules)
    }
}