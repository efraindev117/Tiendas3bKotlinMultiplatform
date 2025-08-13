package com.tienda3b.app.core.data.di

import com.tienda3b.app.core.common.di.DispatchersModule
import com.tienda3b.app.core.common.di.ioDispatcherModule
import com.tienda3b.app.core.database.di.databaseModule
import com.tienda3b.app.core.network.di.ktorEngineConfigurationModule
import org.koin.dsl.module

val appModule = module {
    includes(
        DispatchersModule,
        ktorEngineConfigurationModule,
        databaseModule,
        repositoryModule
    )
}