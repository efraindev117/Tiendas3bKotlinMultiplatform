package com.tienda3b.feature.list_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tienda3b.app.core.designsystem.component.CatCircle
import com.tienda3b.app.core.designsystem.component.TopAppBarCat
import com.tienda3b.app.core.model.favorite.FavoriteUi
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteList(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {},
    onBack: () -> Unit,
    mViewModel: CatsViewModel = koinViewModel()
) {
    val favorites by mViewModel.favoriteCats.collectAsState()
    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarCat(
                titleBar = "Favoritos",
                onBackClick = onBack,
            )
        },
    ) { innerPadding ->
        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Aún no tienes gatos favoritos 🐱")
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites, key = { it.id }) { fav ->
                    FavoriteRow(
                        item = fav,
                        onClick = { onItemClick(fav.id) },

                        )
                }
            }
        }
    }
}

@Composable
fun FavoriteRow(
    item: FavoriteUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = modifier.clickable { onClick() },
        leadingContent = {
            CatCircle(url = item.imageUrl ?: "")
        },
        headlineContent = {
            Text(text = item.name, fontWeight = FontWeight.Bold)
        },

        supportingContent = null,
        trailingContent = {

        }
    )
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color
    )
}