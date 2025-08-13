package com.tienda3b.app.core.designsystem.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CatTextField(
    modifier: Modifier = Modifier,
    value: String,
    supportingText: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    validateField: () -> Unit = {},
    leadingIcon: ImageVector,
    trailingIcon: (@Composable () -> Unit),
    label: String = "",
    isError: Boolean = false,
    isEnabled: Boolean = true
) {
    TextField(
        modifier = modifier
            .height(75.dp)
            .width(345.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
            validateField()
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Normal
            )
        },
        supportingText = {
            Text(
                text = supportingText,
                fontWeight = FontWeight.Normal
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        trailingIcon = {
            trailingIcon()
        },
        singleLine = true,
        isError = isError,
        enabled = isEnabled,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Transparent,
            focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            //
            disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
            disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
            disabledSupportingTextColor = MaterialTheme.colorScheme.tertiary
        )
    )
}