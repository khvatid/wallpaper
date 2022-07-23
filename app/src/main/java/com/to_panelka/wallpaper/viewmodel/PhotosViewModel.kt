package com.to_panelka.wallpaper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.to_panelka.wallpaper.network.models.PhotoModel
import com.to_panelka.wallpaper.network.repository.PhotosRepository

class PhotosViewModel(private val slug: String) : ViewModel() {


    private val repository: PhotosRepository = PhotosRepository()
    val photos: LiveData<List<PhotoModel>> = repository.photos

    fun getPhotos() = repository.getPhotos(slug = slug)

    init {
        getPhotos()
    }
}