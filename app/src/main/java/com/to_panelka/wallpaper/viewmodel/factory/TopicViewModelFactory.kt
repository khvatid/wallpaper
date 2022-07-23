package com.to_panelka.wallpaper.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.to_panelka.wallpaper.viewmodel.TopicViewModel

class TopicViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopicViewModel() as T
    }
}