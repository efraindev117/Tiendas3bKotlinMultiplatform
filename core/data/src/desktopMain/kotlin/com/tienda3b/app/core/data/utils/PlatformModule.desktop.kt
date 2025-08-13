package com.tienda3b.app.core.data.utils

import org.koin.core.module.Module
import org.koin.dsl.module

actual val getPlatformDataModule: IPlatformDependentDataModule
    get() = DesktopPlatformDependentDataModule()


actual val platformModule: Module
    get() = module {
        single<IPlatformDependentDataModule> { getPlatformDataModule }
        single<INetworkMonitor> { getPlatformDataModule.networkMonitor }
    }