package com.tienda3b.feature.auth.navigation

import androidx.navigation.NavController

const val ROUTE_LOGIN  = "auth/login"
const val ROUTE_SIGNUP = "auth/signup"
const val ROUTE_FORGOT = "auth/forgot"


fun NavController.navigateToSignUpScreen() {
    navigate(
        route = ROUTE_SIGNUP
    )
}

fun NavController.navigateToForgetToPasswordScreen() {
    navigate(
        route = ROUTE_FORGOT
    )
}