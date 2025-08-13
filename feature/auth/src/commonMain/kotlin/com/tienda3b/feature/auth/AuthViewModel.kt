package com.tienda3b.feature.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tienda3b.app.core.data.repository.AuthResult
import com.tienda3b.app.core.domain.usescase.auth.AuthUsesCase
import com.tienda3b.app.core.model.auth.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)

class AuthViewModel(
    private val auth: AuthUsesCase
) : ViewModel() {

    private val _ui = MutableStateFlow(AuthUiState())
    val ui: StateFlow<AuthUiState> = _ui

    suspend fun isLoggedInOnce(): Boolean = auth.isLoggedInOnce()

    val isLoggedIn: StateFlow<Boolean> =
        auth.observeAuthState()
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    //email
    var email: MutableState<String> = mutableStateOf("")
    var emailValidate: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    //password
    var password: MutableState<String> = mutableStateOf("")
    var passwordValidate: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    //username
    var username: MutableState<String> = mutableStateOf("")
    var usernameValidate: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrorMsg: MutableState<String> = mutableStateOf("")

    //Validate email
    fun validateEmail() {
        val value = email.value.trim()
        if (value.isEmpty() || !value.contains("@")) {
            emailValidate.value = true
            emailErrMsg.value = "Correo no válido"
        } else {
            emailValidate.value = false
            emailErrMsg.value = ""
        }
    }

    fun validateUsername() {
        val v = username.value.trim()
        if (v.isEmpty()) {
            usernameValidate.value = true
            usernameErrorMsg.value = "Ingresa tu nombre"
        } else {
            usernameValidate.value = false
            usernameErrorMsg.value = ""
        }
    }

    //Validate password
    fun validatePassword() {
        val p = password.value
        if (p.isEmpty() || p.length < 6) {
            passwordValidate.value = true
            passwordErrMsg.value = "Introduce al menos 6 carácteres."
        } else {
            passwordValidate.value = false
            passwordErrMsg.value = ""
        }
    }

    suspend fun deleteAllAccounts(): Boolean {
        _ui.update { it.copy(isLoading = true, error = null) }
        return when (val r = auth.deleteAll()) {
            is AuthResult.Success -> {
                _ui.update { it.copy(isLoading = false, user = null, error = null) }
                true
            }
            is AuthResult.Error -> {
                _ui.update { it.copy(isLoading = false, error = r.message) }
                false
            }
        }
    }


    fun signUp(name: String, email: String, password: String) {
        _ui.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val r = auth.signUp(name, email, password)) {
                is AuthResult.Success -> {
                    _ui.update { it.copy(isLoading = false, user = r.data, error = null) }
                }
                is AuthResult.Error -> {
                    _ui.update { it.copy(isLoading = false, error = r.message) }
                }
            }
        }
    }

    fun login(email: String, password: String) {
        _ui.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val r = auth.login(email, password)) {
                is AuthResult.Success -> {
                    _ui.update { it.copy(isLoading = false, user = r.data, error = null) }
                }
                is AuthResult.Error -> {
                    _ui.update { it.copy(isLoading = false, error = r.message) }
                }
            }
        }
    }


    fun logout() {
        _ui.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val r = auth.logout()) {
                is AuthResult.Success -> {
                    _ui.update { it.copy(isLoading = false, user = null, error = null) }
                }
                is AuthResult.Error -> {
                    _ui.update { it.copy(isLoading = false, error = r.message) }
                }
            }
        }
    }

}