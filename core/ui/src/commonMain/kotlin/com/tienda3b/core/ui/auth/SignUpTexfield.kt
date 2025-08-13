package com.tienda3b.core.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_credential
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_password
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_username


@Composable
fun TextFieldAuthBoxSignUp(
    modifier: Modifier = Modifier,
    emailValue: String,
    usernameValue: String,
    passwordValue: String,
    usernameSupportingText: String,
    emailSupportingText: String,
    passwordSupportingText: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    usernameTrailingIcon: @Composable () -> Unit,
    emailTrailingIcon: @Composable () -> Unit,
    passwordTrailingIcon: @Composable () -> Unit,
    passwordVisualTransformation: VisualTransformation,
    isErrorEmail: Boolean = false,
    isErrorUsername: Boolean = false,
    isErrorPassword: Boolean = false,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        content = {
            Column(modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                CatTextField(
                    modifier = modifier.padding(top = 8.dp),
                    value = usernameValue,
                    supportingText = usernameSupportingText,
                    onValueChange = onUsernameChange,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    visualTransformation = VisualTransformation.None,
                    leadingIcon = ic_credential,
                    trailingIcon = { usernameTrailingIcon() },
                    label = "Nombre completo",
                    isError = isErrorUsername
                )

                CatTextField(
                    modifier = modifier.padding(top = 16.dp),
                    value = emailValue,
                    supportingText = emailSupportingText,
                    onValueChange = onEmailChange,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None,
                    leadingIcon = ic_username,
                    trailingIcon = { emailTrailingIcon() },
                    label = "Correo electronico",
                    isError = isErrorEmail
                )

                CatTextField(
                    modifier = modifier.padding(top = 16.dp),
                    value = passwordValue,
                    supportingText = passwordSupportingText,
                    onValueChange = onPasswordChange,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = passwordVisualTransformation,
                    leadingIcon = ic_password,
                    trailingIcon = {
                        passwordTrailingIcon()
                    },
                    label = "Contraseña",
                    isError = isErrorPassword
                )
            }
        }
    )
}