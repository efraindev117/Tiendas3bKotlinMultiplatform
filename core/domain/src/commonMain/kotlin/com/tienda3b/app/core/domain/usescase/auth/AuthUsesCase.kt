package com.tienda3b.app.core.domain.usescase.auth

data class AuthUsesCase(
    val signUp: SignUpUseCase,
    val login: LoginUseCase,
    val resetPassword: ResetPasswordUseCase,
    val deleteAll: DeleteAllUsesCase,
    val logout: LogoutUseCase,
    val observeAuthState: ObserveAuthStateUseCase,
    val isLoggedInOnce: IsLoggedInUseCase
)