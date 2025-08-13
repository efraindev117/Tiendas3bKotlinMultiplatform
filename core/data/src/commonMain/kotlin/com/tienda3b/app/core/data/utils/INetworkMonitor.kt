package com.tienda3b.app.core.data.utils

import kotlinx.coroutines.flow.Flow

interface INetworkMonitor {
    val isOnline: Flow<Boolean>
}