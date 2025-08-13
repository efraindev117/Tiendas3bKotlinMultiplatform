package com.tienda3b.feature.list_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tienda3b.feature.list_detail.CatsListDetailScreen


const val LIST_CAT_GRAPH = "cat_graph"


fun NavController.navigateToDetailCat(id: String) {
    navigate("detail_cat_route/$id")
}

fun NavController.navigateToFavoriteList() {
    navigate(FAV_LIST_ROUTE)
}

fun NavGraphBuilder.catListGraph(
    onItemClick: (String) -> Unit,
    goToFavoriteList: () -> Unit,
    onBack: () -> Unit
) {
    navigation(
        route = LIST_CAT_GRAPH,
        startDestination = LIST_CAT_ROUTE
    ) {
        listCatScreen(
            onItemClick = onItemClick,
            goToFavoriteList = goToFavoriteList,
            onBack = onBack
        )
        composable(
            route = DETAIL_CAT_ROUTE,
            arguments = listOf(navArgument(ARG_CAT_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(ARG_CAT_ID) ?: return@composable
            CatsListDetailScreen(catId = id, onBack = onBack)
        }
    }
}