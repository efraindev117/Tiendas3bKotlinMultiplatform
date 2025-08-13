package com.tienda3b.core.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tienda3b.app.core.designsystem.component.CatTextField
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_email
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_password
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_username

@Composable
fun BoxTexField(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        CatTextField(
            value = "telefono o email",
            supportingText = "",
            onValueChange = {

            },
            keyboardOptions = KeyboardOptions.Default,
            visualTransformation = VisualTransformation.None,
            leadingIcon = ic_email,
            trailingIcon = { /*TODO*/ })

        CatTextField(
            value = "Constraseña",
            supportingText = "",
            onValueChange = {

            },
            keyboardOptions = KeyboardOptions.Default,
            visualTransformation = VisualTransformation.None,
            leadingIcon = ic_email,
            trailingIcon = { /*TODO*/ }
        )
    }
}


@Composable
fun TextFieldAuthBox(
    modifier: Modifier = Modifier,
    usernameValue: String,
    passwordValue: String,
    usernameValidate: () -> Unit,
    passwordValidate: () -> Unit,
    supportingTextUsername: String,
    supportingTextPassword: String,
    onValueUsernameChange: (String) -> Unit,
    onValuePasswordChange: (String) -> Unit,
    trailingIconUsername: (@Composable () -> Unit),
    trailingIconPassword: (@Composable () -> Unit),
    passwordVisualTransform: VisualTransformation
) {
    Box(modifier = modifier,
        content = {
            Column(modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                CatTextField(
                    modifier = modifier,
                    value = usernameValue,
                    supportingText = supportingTextUsername,
                    onValueChange = onValueUsernameChange,
                    validateField = usernameValidate,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None,
                    leadingIcon = ic_username,
                    trailingIcon = { trailingIconUsername() },
                    label = "Correo electrónico"
                )
                CatTextField(
                    modifier = modifier.padding(top = 32.dp),
                    value = passwordValue,
                    supportingText = supportingTextPassword,
                    onValueChange = onValuePasswordChange,
                    validateField = passwordValidate,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = passwordVisualTransform,
                    leadingIcon = ic_password,
                    trailingIcon = {
                        trailingIconPassword()
                    },
                    label = "Contraseña"
                )
            }
        }
    )
}