package com.to_panelka.wallpaper.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.to_panelka.wallpaper.network.models.TopicModel
import com.to_panelka.wallpaper.ui.composable.TopicCard
import com.to_panelka.wallpaper.viewmodel.TopicViewModel

@Composable
fun TopicsScreen(
    onClickToTopic : (String)->Unit = {},
    viewModel: TopicViewModel
) {
    val topics by viewModel.topics.observeAsState(listOf())
    val listState = rememberLazyGridState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Topics",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(5.dp))
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState) {
            topics.forEach { topicModel ->
                item {
                    when (topicModel) {
                        TopicModel.Loading -> Text(text = "/loading/")
                        is TopicModel.Topic -> TopicCard(topic = topicModel, onClick = onClickToTopic)
                    }
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Button(onClick = { viewModel.getTopics()}) {
                    Text(text = "Load more")
                }
            }
        }
    }


}