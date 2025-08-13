package com.shared.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shared.ui.rememberAppState
import com.tienda3b.app.core.data.utils.INetworkMonitor
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_wifi
import com.tienda3b.feature.auth.AuthViewModel
import com.tienda3b.feature.auth.navigation.AUTH_GRAPH
import com.tienda3b.feature.auth.navigation.ROUTE_LOGIN
import com.tienda3b.feature.auth.navigation.authGraph
import com.tienda3b.feature.list_detail.navigation.LIST_CAT_GRAPH
import com.tienda3b.feature.list_detail.navigation.catListGraph
import com.tienda3b.feature.list_detail.navigation.navigateToDetailCat
import com.tienda3b.feature.list_detail.navigation.navigateToFavoriteList
import org.koin.compose.viewmodel.koinViewModel

const val GATE_ROUTE = "gate"

@Composable
internal fun RootHost(
    modifier: Modifier = Modifier,
    networkMonitor: INetworkMonitor,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val rootController = rememberNavController()
    val catAppState = rememberAppState(
        modifier = modifier,
        networkMonitor = networkMonitor,
    )
    val isOffline by catAppState.isOffline.collectAsState()
    val message by remember(isOffline) {
        derivedStateOf {
            if (isOffline) "Revisa que el modem se encuentre conectado." else "$isOffline"
        }
    }

    var startRoute by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        startRoute = if (authViewModel.isLoggedInOnce()) LIST_CAT_GRAPH else AUTH_GRAPH
        println("startRoute resolved -> $startRoute") // debug
    }
    if (startRoute == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // 2) respaldo reactivo: si aparece sesión, navega a lista
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    LaunchedEffect(isLoggedIn) {
        println("observeAuthState -> $isLoggedIn") // debug
        if (isLoggedIn) {
            rootController.navigate(LIST_CAT_GRAPH) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
    Scaffold(
        topBar = {
            AnimatedVisibility(
                modifier = modifier.offset(y = 50.dp).padding(16.dp),
                visible = isOffline,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit  = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
            ) {
                Surface( tonalElevation = 2.dp ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        FilterChip(
                            selected = true,
                            onClick = {  },
                            label = { Text("Revisa que el módem se encuentre conectado.") },
                            leadingIcon = {
                                Icon(
                                    imageVector = ic_wifi,
                                    contentDescription = "Sin conexión"
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.errorContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onErrorContainer,
                                selectedLeadingIconColor = MaterialTheme.colorScheme.onErrorContainer
                            )
                        )
                    }
                }
            }
        },
        bottomBar = {

        }
    ) { paddingValues ->
        NavHost(
            modifier = modifier.padding(),
            navController = rootController,
            startDestination = startRoute!!,
            enterTransition = { screenSlideIn() },
            exitTransition = { screenFadeOut() },
            popEnterTransition = { screenFadeIn() },
            popExitTransition = { screenSlideOut() }
        ) {
            authGraph(
                navController = rootController,
                goToCatList = {
                    rootController.navigate(LIST_CAT_GRAPH) {
                        popUpTo(AUTH_GRAPH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
            catListGraph(
                onItemClick = { id -> rootController.navigateToDetailCat(id) },
                goToFavoriteList = {
                    rootController.navigateToFavoriteList()
                },
                onBack = {
                    rootController.popBackStack()
                }
            )
        }
    }

}