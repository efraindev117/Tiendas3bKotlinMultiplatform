package com.tienda3b.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tienda3b.app.core.designsystem.component.CatButtonFilled
import com.tienda3b.app.core.designsystem.component.TopAppBarCat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ForgetToPasswordScreen(
    mViewModel: AuthViewModel = koinViewModel(),
    onBackButtonClick: () -> Unit,
    goToLogin: () -> Unit
) {
    val ui by mViewModel.ui.collectAsState()
    var done by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }
    var isWaiting by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBarCat(
            titleBar = "Eliminar contraseña",
            onBackClick = {
                onBackButtonClick()
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (done) "Contraseña borrada" else "Olvidé mi contraseña",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            if (!done) {
                Text(
                    text = "Presiona el botón para borrar la contraseña del correo capturado.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            localError?.let {
                Spacer(Modifier.height(8.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(24.dp))


            CatButtonFilled(
                buttonText = "Iniciar sesión",
                onClick = {
                    scope.launch {
                        val ok = mViewModel.deleteAllAccounts()
                        if (ok) {
                            done = true
                            isWaiting = true
                            delay(2000)
                            isWaiting = false
                            goToLogin()
                        } else {
                            localError = mViewModel.ui.value.error ?: "No hay ninguna cuenta activa"
                        }
                    }
                },
            )
        }
    }
}