package com.tienda3b.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tienda3b.feature.auth.ForgetToPasswordScreen
import com.tienda3b.feature.auth.LoginScreen
import com.tienda3b.feature.auth.SignUpScreen
import com.tienda3b.feature.list_detail.navigation.LIST_CAT_GRAPH

const val AUTH_GRAPH = "auth_graph"

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    goToCatList: () -> Unit = {}
) {
    navigation(
        route = AUTH_GRAPH,
        startDestination = ROUTE_LOGIN
    ) {
        composable(route = ROUTE_LOGIN) {
            LoginScreen(
                goToSignUp = { navController.navigateToSignUpScreen() },
                goToRememberPassword = { navController.navigateToForgetToPasswordScreen() },
                goToCatList = {
                    navController.navigate(LIST_CAT_GRAPH) {
                        popUpTo(AUTH_GRAPH) { inclusive = true }
                        launchSingleTop = true
                    }
                    goToCatList()
                }
            )
        }

        composable(route = ROUTE_SIGNUP) {
            SignUpScreen(
                goToList = {
                    navController.navigate(LIST_CAT_GRAPH) {
                        popUpTo(AUTH_GRAPH) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onBackButtonClick = { navController.popBackStack() }
            )
        }

        composable(route = ROUTE_FORGOT) {
            ForgetToPasswordScreen(
                goToLogin = {
                    navController.popBackStack(ROUTE_LOGIN, inclusive = false)
                },
                onBackButtonClick = { navController.popBackStack() }
            )
        }
    }
}