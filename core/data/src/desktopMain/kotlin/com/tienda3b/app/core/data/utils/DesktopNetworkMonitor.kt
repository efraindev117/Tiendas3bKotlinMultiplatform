package com.tienda3b.app.core.data.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.InetSocketAddress
import java.net.Socket

class DesktopNetworkMonitor : INetworkMonitor {
    override val isOnline: Flow<Boolean> = flow {
        var lastState = checkInternetConnection()
        emit(lastState)

        while (true) {
            val currentSate = checkInternetConnection()
            if (currentSate != lastState) {
                emit(currentSate)
                lastState = currentSate
            }
            delay(2_000)
        }
    }

    private fun checkInternetConnection(): Boolean {
        return try {
            Socket().use { socket ->
                socket.connect(InetSocketAddress("1.1.1.1", 53), 1500)
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}