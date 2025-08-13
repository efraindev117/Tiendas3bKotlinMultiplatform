package com.tienda3b.feature.list_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tienda3b.app.core.designsystem.utils.TiendasIcons
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_format_list
import com.tienda3b.app.core.model.CatsUiModelItem
import com.tienda3b.core.ui.CatListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsListScreen(
    mViewModel: CatsViewModel = koinViewModel(),
    onItemClick: (String) -> Unit,
    goToFavoriteList:()-> Unit
) {
    val items by mViewModel.items.collectAsState()
    val ui by mViewModel.ui.collectAsState()
    val listState = rememberLazyListState()

    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val favoriteIds by mViewModel.favoriteIdws.collectAsState(initial = emptySet<String>())

    LaunchedEffect(listState) {
        snapshotFlow {
            val info = listState.layoutInfo
            val lastVisible = info.visibleItemsInfo.lastOrNull()?.index ?: -1
            val total = info.totalItemsCount
            lastVisible to total
        }.distinctUntilChanged()
            .collect { (lastVisible, total) ->
                val threshold = 5
                if (!ui.isLoading && total > 0 && lastVisible >= total - threshold) {
                    mViewModel.loadNext()
                }
            }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    goToFavoriteList()
                },
                icon = { Icon(imageVector = ic_format_list, "favorite cats list") },
                text = { Text(text = "Favoritos") }
            )
        }
    ) { padding ->
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            state = rememberPullToRefreshState(),
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    mViewModel.refresh()
                    isRefreshing = false
                }
            }
        ) {
            Box(Modifier.fillMaxSize().padding(padding)) {
                if (items.isEmpty() && ui.isLoading) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                    return@Box
                }

                if (items.isEmpty() && ui.error != null) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(ui.error ?: "Error", color = MaterialTheme.colorScheme.error)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { mViewModel.loadNext() }) { Text("Reintentar") }
                    }
                    return@Box
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        count = items.size,
                        key = { idx -> items[idx].id }
                    ) { idx ->
                        val cat = items[idx]
                        CatListItem(
                            item = cat,
                            goToDetail = {
                                onItemClick(cat.id)
                            }, onFavoriteClick = {
                                mViewModel.toggleFavorite(cat.id,cat.name,cat.image.url)
                            }, isFavorite =  favoriteIds.contains(cat.id),
                        )
                    }
                    if (ui.isLoading && items.isNotEmpty()) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    if (!ui.isLoading && ui.error != null && items.isNotEmpty()) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(ui.error ?: "Error", color = MaterialTheme.colorScheme.error)
                                Spacer(Modifier.width(12.dp))
                                OutlinedButton(onClick = { mViewModel.loadNext() }) {
                                    Text("Reintentar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
