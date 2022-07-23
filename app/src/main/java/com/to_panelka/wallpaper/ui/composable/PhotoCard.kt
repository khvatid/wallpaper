package com.to_panelka.wallpaper.ui.composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.to_panelka.wallpaper.R
import com.to_panelka.wallpaper.network.models.PhotoModel

@Composable
fun PhotoCard(photo: PhotoModel.Photo, onClick: () -> Unit = {}) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo.urls.small)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_outline_photo),
        contentDescription = photo.color,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(5.dp)
            .size(150.dp)
            .clip(RoundedCornerShape(25))
            .clickable { onClick() },
    )
}

@Composable
@Preview
fun Preview() {
    PhotoCard(photo = PhotoModel.Photo())
}