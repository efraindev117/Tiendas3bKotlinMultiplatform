package com.tienda3b.feature.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_clear
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_visibility
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_visibilityOff
import com.tienda3b.core.ui.auth.BoxAuthBox
import com.tienda3b.core.ui.auth.TextFieldAuthBox
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit = {},
    goToSignUp: () -> Unit,
    goToRememberPassword: () -> Unit,
    goToCatList: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        LoginScreenContainer(
            modifier,
            paddingValues,
            goToSignUp,
            goToRememberPassword,
            goToCatList
        )
    }
}

@Composable
fun LoginScreenContainer(
    modifier: Modifier,
    paddingValues: PaddingValues,
    goToSignUp: () -> Unit,
    goToRememberPassword: () -> Unit,
    goToCatList: () -> Unit,
    mViewModel: AuthViewModel = koinViewModel()
) {
    ConstraintLayout(
        modifier.fillMaxSize().padding(paddingValues)
    ) {
        val (boxTexField, boxButtons, dailyLogo,errorText) = createRefs()
        var passwordVisibility by remember { mutableStateOf(false) }
        val ui by mViewModel.ui.collectAsState()

        Text(
            text = "Tiendas 3B CatList",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(dailyLogo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(boxTexField.top)
                },
        )

        TextFieldAuthBox(
            modifier = modifier.constrainAs(boxTexField) {
                top.linkTo(dailyLogo.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            usernameValue = mViewModel.email.value,
            passwordValue = mViewModel.password.value,
            supportingTextPassword = mViewModel.passwordErrMsg.value,
            supportingTextUsername = mViewModel.emailErrMsg.value,
            onValueUsernameChange = { mViewModel.email.value = it },
            onValuePasswordChange = { mViewModel.password.value = it },
            trailingIconUsername = {
                val icon = if (passwordVisibility) {
                    ic_clear
                } else {
                    ic_clear
                }
                IconButton(
                    onClick = { mViewModel.email.value = "" },
                    enabled = mViewModel.email.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            trailingIconPassword = {
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
            usernameValidate = {
                mViewModel.validateEmail()
            },
            passwordValidate = {
                mViewModel.validatePassword()
            },
            passwordVisualTransform = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        )

        ui.error?.let { msg ->
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .constrainAs(errorText) {
                    top.linkTo(boxTexField.bottom, margin = 8.dp)
                    start.linkTo(parent.start); end.linkTo(parent.end)
                }
            )
        }

        val canSubmit =
            mViewModel.email.value.isNotBlank() &&
                    mViewModel.password.value.isNotBlank() &&
                    !mViewModel.emailValidate.value &&         // sin error en email
                    !mViewModel.passwordValidate.value &&      // sin error en password
                    !ui.isLoading

        BoxAuthBox(modifier = modifier.padding(top = 32.dp).constrainAs(boxButtons) {
            top.linkTo(boxTexField.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, goToSignUp = {
            goToSignUp()
        }, goToRememberPassword = {
            goToRememberPassword()
        }, goToCatList = {
            mViewModel.login(
                email = mViewModel.email.value,
                password = mViewModel.password.value
            )
        }, enabled = canSubmit,
            isLoading = ui.isLoading
        )
        LaunchedEffect(ui.user?.id) {
            if (ui.user != null) {
                goToCatList()
            }
        }
    }
}