package com.tienda3b.core.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tienda3b.app.core.designsystem.component.CatButtonFilled
import com.tienda3b.app.core.designsystem.component.CatButtonOutline

@Composable
fun BoxAuthBox(
    modifier: Modifier = Modifier,
    goToSignUp: () -> Unit,
    goToRememberPassword: () -> Unit,
    goToCatList: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = {
                goToRememberPassword()
            }) {
                Text(
                    text = "¿Olvidaste la Contraseña?",
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        CatButtonFilled(
            buttonText = if (isLoading) "Iniciando..." else "Iniciar sesión",
            onClick = goToCatList,
            enabled = enabled && !isLoading
        )
        Spacer(Modifier.height(16.dp))
        CatButtonOutline(
            buttonText = "Regístrate",
            onClick = goToSignUp
        )
    }
}