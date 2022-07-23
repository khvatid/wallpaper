package com.to_panelka.wallpaper.network

import com.to_panelka.wallpaper.network.models.PhotoModel
import com.to_panelka.wallpaper.network.models.TopicModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface NetworkInstance {

    @GET("/topics")
    suspend fun topics(
        @Query("page") page: Int,
        @Query("client_id") clientId: String = NetworkConstant.accessToken
    ): List<TopicModel.Topic>

    @GET("/topics/{slug}/photos/")
    suspend fun topicPhotos(
        @Path("slug") slug: String,
        @Query("page") page: Int,
        @Query("client_id") clientId: String = NetworkConstant.accessToken
    ): List<PhotoModel.Photo>

    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstant.apiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //fun create(): NetworkInstance = retrofit.create(NetworkInstance::class.java)

        private var INSTANCE: NetworkInstance? = null

        @OptIn(InternalCoroutinesApi::class)
        fun get(): NetworkInstance {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = retrofit.create(NetworkInstance::class.java)
                    INSTANCE = instance
                }
                return instance!!
            }

        }


    }

}