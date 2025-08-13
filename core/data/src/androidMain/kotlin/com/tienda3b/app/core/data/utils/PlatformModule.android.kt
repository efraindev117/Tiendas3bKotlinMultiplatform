package com.tienda3b.app.core.data.utils

import com.tienda3b.app.core.common.di.AppDispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


val AndroidDataModule = module {
    single<INetworkMonitor> {
        ConnectivityManagerNetworkMonitor(androidContext(), get(named(AppDispatchers.IO.name)))
    }

    single {
        AndroidPlatformDependentDataModule(
            context = androidContext(),
            dispatcher = get(named(AppDispatchers.IO.name)),
            scope = get(named("ApplicationScope")),
        )
    }
}

actual val platformModule: Module = AndroidDataModule

actual val getPlatformDataModule: IPlatformDependentDataModule
    get() = GlobalContext.get().get()