package com.to_panelka.wallpaper.viewmodel.factory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.to_panelka.wallpaper.viewmodel.PhotosViewModel

class PhotosViewModelFactory(private val slug : String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.i("PHOTO FACTORY VM", "$slug viewModel create")
        return PhotosViewModel(slug = slug) as T
    }
}