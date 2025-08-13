package com.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shared.navigation.RootHost
import com.tienda3b.app.core.data.utils.INetworkMonitor
import com.tienda3b.app.core.designsystem.theme.Tiendas3bTheme
import com.tienda3b.feature.list_detail.CatsListScreen
import org.koin.compose.koinInject

@Composable
fun ShareApp(
    modifier: Modifier = Modifier,
    networkMonitor: INetworkMonitor = koinInject(),
) {

    RootApp(modifier, networkMonitor)
}

@Composable
fun RootApp(modifier: Modifier, networkMonitor: INetworkMonitor) {
    Tiendas3bTheme {
        RootHost(
            modifier = modifier,
            networkMonitor = networkMonitor
        )
    }
}