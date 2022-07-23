package com.to_panelka.wallpaper.network.models



sealed class TopicModel {
    object Loading : TopicModel()
    data class Topic(
    val id : String = "",
    val slug : String ="",
    val title : String ="",
    val description: String = "",
    val links : Url = Url(),
    val cover_photo : PhotoModel.Photo = PhotoModel.Photo()
    ) : TopicModel() {

        data class Url(
            val self : String = "",
            val photos : String = ""
        )
        data class Owner(
           val id: String = "",
           val name : String = ""
        )

    }


}
