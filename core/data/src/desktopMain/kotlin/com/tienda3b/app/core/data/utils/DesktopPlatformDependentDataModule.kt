package com.tienda3b.app.core.data.utils

class DesktopPlatformDependentDataModule : IPlatformDependentDataModule {
    override val networkMonitor: INetworkMonitor by lazy {
        DesktopNetworkMonitor()
    }
}