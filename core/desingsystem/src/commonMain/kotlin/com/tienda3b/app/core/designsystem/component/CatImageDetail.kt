package com.tienda3b.app.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CatImageDetail(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val customImageLoader = ImageLoader.Builder(LocalPlatformContext.current)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .crossfade(true)
        .build()
    AsyncImage(
        imageLoader = customImageLoader,
        model = ImageRequest.Builder(LocalPlatformContext.current).data(url).crossfade(300).build(),
        contentDescription = "User avatar",
        contentScale = contentScale,
        modifier = modifier.fillMaxWidth().height(300.dp),
        clipToBounds = true
    )
}