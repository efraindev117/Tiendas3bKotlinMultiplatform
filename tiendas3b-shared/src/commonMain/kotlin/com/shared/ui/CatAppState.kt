package com.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tienda3b.app.core.data.utils.INetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
internal fun rememberAppState(
    modifier: Modifier = Modifier,
    networkMonitor: INetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): CatAppState {
    return remember(
        navController,
        coroutineScope,
        networkMonitor,
    ) {
        CatAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor,
        )
    }
}

@Stable
class CatAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: INetworkMonitor,
) {
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )


}