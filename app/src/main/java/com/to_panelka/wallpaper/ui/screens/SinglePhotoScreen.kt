package com.to_panelka.wallpaper.ui.screens

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.to_panelka.wallpaper.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun SinglePhotoScreen(url: String) {
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var isSet by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "preview",
            contentScale = ContentScale.Crop,
            onSuccess = {
                bitmap = it.result.drawable.toBitmap()
            },
            modifier = Modifier.fillMaxSize()
        )
    }
    if (bitmap != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.padding(10.dp),
                enabled = !isSet,
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                        isSet = true
                        val wallpaperManager = WallpaperManager.getInstance(context)
                        wallpaperManager.setBitmap(bitmap)
                        isSet = false
                    }
                }) {
                Text(text = "Set as Wallpaper")
            }
        }
    }

}