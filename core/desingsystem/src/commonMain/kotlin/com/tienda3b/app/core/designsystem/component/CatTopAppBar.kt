package com.tienda3b.app.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tienda3b.app.core.designsystem.utils.TiendasIcons.ic_back

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCat(
    modifier: Modifier = Modifier,
    titleBar: String = "",
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = titleBar)
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = ic_back,
                    contentDescription = "navigationBack"
                )
            }
        },
        actions = {

        })

}