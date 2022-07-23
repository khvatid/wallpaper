package com.to_panelka.wallpaper.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.to_panelka.wallpaper.network.NetworkInstance
import com.to_panelka.wallpaper.network.models.PhotoModel
import com.to_panelka.wallpaper.network.models.TopicModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhotosRepository {
    private val coroutineScope : CoroutineScope = CoroutineScope(Dispatchers.Main)
    private val instance = NetworkInstance.get()

    private val _photos = MutableLiveData(emptyList<PhotoModel>())
    private var page = 1
    val photos : LiveData<List<PhotoModel>> get() = _photos

    fun getPhotos(slug : String) = coroutineScope.launch(Dispatchers.IO) {
        val addLoading = _photos.value!!.toMutableList()
        addLoading.add(PhotoModel.Loading)
        try {
            delay(1000)
            val photo = instance.topicPhotos(slug = slug,page = page)
            if(photo.isEmpty())throw Throwable("not new Photo")
            val added = _photos.value!!.toMutableList()
            added.addAll(photo)
            _photos.postValue(added)
            page +=1
            Log.i("REPO","load topics ${photos.value.toString()}")
        }catch (t : Throwable){
            t.printStackTrace()
            Log.i("REPO","LOAD FAIL!!!!!!")
        }
    }

}