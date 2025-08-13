package com.tienda3b.app.core.designsystem.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jdk.jfr.Enabled

@Composable
fun CatButtonFilled(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier.width(220.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(25),
        enabled = enabled
    ) {
        Text(
            text = buttonText,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun CatButtonOutline(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    OutlinedButton(
        modifier = modifier.width(220.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        enabled = enabled
    ) {
        Text(
            text = buttonText,
            color = MaterialTheme.colorScheme.primary
        )
    }
}