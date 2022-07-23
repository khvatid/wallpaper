package com.to_panelka.wallpaper.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.to_panelka.wallpaper.network.models.TopicModel
import com.to_panelka.wallpaper.network.repository.TopicRepository

class TopicViewModel: ViewModel() {

    private val repository : TopicRepository = TopicRepository()

    val topics : LiveData<List<TopicModel>> = repository.topics

    fun getTopics() = repository.getTopics()

    init {
        getTopics()
        Log.i("INIT#->","LOAD TOPIC FROM VIEW MODEL")
    }

}