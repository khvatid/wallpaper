package com.to_panelka.wallpaper.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.to_panelka.wallpaper.network.models.PhotoModel
import com.to_panelka.wallpaper.ui.composable.PhotoCard
import com.to_panelka.wallpaper.viewmodel.PhotosViewModel


@Composable
fun PhotoListScreen(onClick: (String)->Unit, viewModel: PhotosViewModel) {
    val photos by viewModel.photos.observeAsState(listOf())
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        photos.forEach { photoModel ->
            when (photoModel) {
                PhotoModel.Loading -> {}
                is PhotoModel.Photo -> item {
                    PhotoCard(photo = photoModel, onClick = {onClick(photoModel.urls.raw)})
                }
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Button(onClick = { viewModel.getPhotos() }) {
                Text(text = "more")
            }
        }
    }
}