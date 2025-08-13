package com.tienda3b.app.core.model.auth

data class User(
    val id: String,
    val name: String,
    val email: String,
)

data class AuthSession(
    val userId: String,
    val email: String,
)