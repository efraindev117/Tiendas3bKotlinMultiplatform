package com.tienda3b.feature.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tienda3b.app.core.designsystem.component.CatButtonFilled
import com.tienda3b.app.core.designsystem.component.TopAppBarCat
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_clear
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_visibility
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_visibilityOff
import com.tienda3b.core.ui.auth.TextFieldAuthBoxSignUp
import org.koin.compose.viewmodel.koinViewModel
import kotlin.Unit

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    goToList: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCat(
                titleBar = "Registrar usuario",
                onBackClick = {
                    onBackButtonClick()
                }
            )
        }
    ) { paddingValues ->
        SignUpScreenContent(
            modifier,
            paddingValues,
            goToList,
        )
    }
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    goToList: () -> Unit,
    mViewModel: AuthViewModel = koinViewModel()
) {
    val ui by mViewModel.ui.collectAsState()
    var passwordVisibility by remember { mutableStateOf(false) }
    ConstraintLayout(modifier = modifier.padding(paddingValues)) {
        val (boxTexField, button, errorText) = createRefs()
        val topGuideline = createGuidelineFromTop(.01f)

        ui.error?.let { msg ->
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.constrainAs(errorText) {
                    top.linkTo(boxTexField.bottom, margin = 8.dp)
                    start.linkTo(parent.start); end.linkTo(parent.end)
                }
            )
        }

        TextFieldAuthBoxSignUp(
            modifier = modifier.constrainAs(boxTexField) {
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            usernameValue = mViewModel.username.value,
            emailValue = mViewModel.email.value,
            passwordValue = mViewModel.password.value,
            usernameSupportingText = mViewModel.usernameErrorMsg.value,
            emailSupportingText = mViewModel.emailErrMsg.value,
            passwordSupportingText = mViewModel.passwordErrMsg.value,
            onUsernameChange = { mViewModel.username.value = it },
            onEmailChange = {
                mViewModel.email.value = it
            },
            onPasswordChange = { mViewModel.password.value = it },
            usernameTrailingIcon = {
                IconButton(
                    onClick = { mViewModel.username.value = "" },
                    enabled = mViewModel.username.value.isNotEmpty()
                ) {
                    Icon(imageVector = ic_clear, contentDescription = null)
                }
            },
            emailTrailingIcon = {
                IconButton(
                    onClick = { mViewModel.email.value = "" },
                    enabled = mViewModel.email.value.isNotEmpty()
                ) {
                    Icon(imageVector = ic_clear, contentDescription = null)
                }
            },
            passwordTrailingIcon = {
                val icon = if (passwordVisibility) {
                    ic_visibility
                } else {
                    ic_visibilityOff
                }
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    enabled = mViewModel.password.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            passwordVisualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            isErrorUsername = mViewModel.usernameValidate.value,
            isErrorPassword = mViewModel.passwordValidate.value,
            isErrorEmail = mViewModel.emailValidate.value,
        )

        val canSubmit =
            mViewModel.username.value.isNotBlank() &&
                    mViewModel.email.value.isNotBlank() &&
                    mViewModel.password.value.isNotBlank() &&
                    !mViewModel.usernameValidate.value &&
                    !mViewModel.emailValidate.value &&
                    !mViewModel.passwordValidate.value &&
                    !ui.isLoading


        CatButtonFilled(
            modifier = modifier.constrainAs(button) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo((ui.error?.let { errorText } ?: boxTexField).bottom, margin = 16.dp)
            },
            onClick = {
                mViewModel.validateUsername()
                mViewModel.validateEmail()
                mViewModel.validatePassword()
                val okNow = !mViewModel.usernameValidate.value &&
                        !mViewModel.emailValidate.value &&
                        !mViewModel.passwordValidate.value
                if (!okNow) return@CatButtonFilled

                mViewModel.signUp(
                    name = mViewModel.username.value.trim(),
                    email = mViewModel.email.value.trim(),
                    password = mViewModel.password.value
                )
            },
            buttonText = if (ui.isLoading) "Creando..." else "Registrar cuenta",
            enabled = canSubmit
        )
        LaunchedEffect(ui.user?.id) {
            if (ui.user != null) {
                goToList()
            }
        }
    }
}