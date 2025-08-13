package com.tienda3b.feature.list_detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tienda3b.feature.list_detail.CatsListDetailScreen
import com.tienda3b.feature.list_detail.CatsListScreen
import com.tienda3b.feature.list_detail.FavoriteList


const val LIST_CAT_ROUTE = "list_cat_route"
const val FAV_LIST_ROUTE = "fav_list_route"
const val DETAIL_CAT_ROUTE = "detail_cat_route/{id}"
const val ARG_CAT_ID = "id"

internal fun NavGraphBuilder.listCatScreen(
    onItemClick: (String) -> Unit,
    goToFavoriteList: () -> Unit,
    onBack: () -> Unit
) {
    composable(LIST_CAT_ROUTE) {
        CatsListScreen(
            onItemClick = onItemClick,
            goToFavoriteList = goToFavoriteList
        )
    }

    composable(
        route = DETAIL_CAT_ROUTE,
        arguments = listOf(navArgument(ARG_CAT_ID) { type = NavType.StringType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString(ARG_CAT_ID) ?: return@composable
        CatsListDetailScreen(catId = id, onBack = onBack)
    }

    composable(
        route = FAV_LIST_ROUTE
    ) {
        FavoriteList(
            onBack = onBack
        )
    }
}