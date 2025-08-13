package com.tienda3b.app.core.data.utils

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class AndroidPlatformDependentDataModule(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
    private val scope: CoroutineScope
) : IPlatformDependentDataModule {

    override val networkMonitor: INetworkMonitor by lazy {
        ConnectivityManagerNetworkMonitor(context = context, ioDispatcher = dispatcher)
    }
}