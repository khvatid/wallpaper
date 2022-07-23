package com.to_panelka.wallpaper.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.to_panelka.wallpaper.network.NetworkConstant
import com.to_panelka.wallpaper.network.NetworkInstance
import com.to_panelka.wallpaper.network.models.TopicModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TopicRepository {

    private val coroutineScope : CoroutineScope = CoroutineScope(Dispatchers.Main)
    private val instance = NetworkInstance.get()

    private val _topics = MutableLiveData(emptyList<TopicModel>())
    private var page = 1
    val topics : LiveData<List<TopicModel>> get() = _topics


    fun getTopics() = coroutineScope.launch(Dispatchers.IO) {
        val addLoading = _topics.value!!.toMutableList()
        addLoading.add(TopicModel.Loading)
        try {
            delay(2000)
            val topic = instance.topics(page = page)
            if(topic.isEmpty())throw Throwable("not new Topics")
            val added = _topics.value!!.toMutableList()
            added.addAll(topic)
            _topics.postValue(added)
            page +=1
            Log.i("REPO","load topics ${topics.value.toString()}")
        }catch (t : Throwable){
            t.printStackTrace()
            Log.i("REPO","LOAD FAIL!!!!!!")
        }
    }

}