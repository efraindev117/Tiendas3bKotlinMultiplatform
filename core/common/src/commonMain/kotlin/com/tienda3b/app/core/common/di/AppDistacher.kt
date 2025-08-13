package com.tienda3b.app.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class AppDispatchers {
    Default,
    IO,
    Unconfined,
}

val DispatchersModule = module {
    includes(ioDispatcherModule)
    single<CoroutineDispatcher>(named(AppDispatchers.Default.name)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(AppDispatchers.Unconfined.name)) { Dispatchers.Unconfined }
    single<CoroutineScope>(named("ApplicationScope")) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}