package com.tienda3b.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tienda3b.app.core.designsystem.component.CatCircle
import com.tienda3b.app.core.designsystem.utils.TiendasIcons
import com.tienda3b.app.core.model.CatsUiModelItem

@Composable
fun CatListItem(
    item: CatsUiModelItem,
    modifier: Modifier = Modifier,
    goToDetail: () -> Unit = {},
    isFavorite: Boolean = false,
    onFavoriteClick: (() -> Unit)? = null,
) {
    ListItem(
        modifier = modifier.clickable(onClick = {
            goToDetail()
        }),
        leadingContent = {
            CatCircle(
                url = item.image.url
            )
        },
        headlineContent = {
            Text(

                text = item.name, fontWeight = FontWeight.Bold
            )
        },
        supportingContent = {
            val sub = buildString {
                if (item.temperament.isNotBlank()) {
                    append(" • ")
                    append(item.temperament)
                }
            }
            Text(sub, maxLines = 2, fontSize = 12.sp)
        },
        trailingContent = {
            IconButton(onClick = { onFavoriteClick?.invoke() }) {
                Icon(
                    imageVector = if (isFavorite) TiendasIcons.ic_favorite_on else TiendasIcons.ic_favorite_off,
                    tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos"
                )
            }
        }
    )
    HorizontalDivider(
        Modifier.fillMaxWidth(),
        DividerDefaults.Thickness,
        DividerDefaults.color
    )
}