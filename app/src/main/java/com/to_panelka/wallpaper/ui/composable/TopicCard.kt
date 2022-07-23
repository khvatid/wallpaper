package com.to_panelka.wallpaper.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.to_panelka.wallpaper.R
import com.to_panelka.wallpaper.network.models.TopicModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicCard(topic: TopicModel.Topic , onClick : (String)->Unit) {
    OutlinedCard(modifier = Modifier.padding(5.dp),onClick = {onClick(topic.slug)}) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = topic.title,
                style = MaterialTheme.typography.titleLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(topic.cover_photo.urls.full)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_outline_photo),
                contentDescription = topic.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25))
            )
            Text(
                text = topic.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}