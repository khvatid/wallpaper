package com.to_panelka.wallpaper

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.to_panelka.wallpaper.ui.screens.PhotoListScreen
import com.to_panelka.wallpaper.ui.screens.SinglePhotoScreen
import com.to_panelka.wallpaper.ui.screens.TopicsScreen
import com.to_panelka.wallpaper.viewmodel.factory.PhotosViewModelFactory
import com.to_panelka.wallpaper.viewmodel.factory.TopicViewModelFactory

@Composable
fun WallNavHost(
    navController: NavHostController
) {
    val owner = LocalViewModelStoreOwner.current
    val application = LocalContext.current.applicationContext as Application

    NavHost(navController = navController, startDestination = "Topics") {
        composable(route = "Topics") {
            TopicsScreen(
                viewModel = viewModel(
                    viewModelStoreOwner = owner!!,
                    key = "Topics",
                    factory = TopicViewModelFactory(application)
                ),
                onClickToTopic = { slug -> navigateToTopicPhotos(navController = navController, slug = slug) }
            )
        }
        composable(
            route = "{slug}/photos",
            arguments = listOf(
                navArgument(
                    name = "slug"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            val slug = it.arguments?.getString("slug")
            PhotoListScreen(onClick = {url -> navigateToSet(navController, url) },
                viewModel(viewModelStoreOwner = owner!!,
                key = slug,
                factory = PhotosViewModelFactory(slug = slug!!)))
        }

        composable(
            route = "set/{url}",
            arguments = listOf(
                navArgument(
                    name = "url"
                ) {
                    type = NavType.StringType
                }
            )
        ){
            val photo = it.arguments?.getString("url")
            val url = "https://images.unsplash.com/"
            SinglePhotoScreen(url = "$url$photo")
        }
    }
}

private fun navigateToSet(
    navController: NavController,
    url : String
){
    navController.navigate(
        route = "set/${url.substringAfter("https://images.unsplash.com/")}")
}

private fun navigateToTopicPhotos(
    navController: NavController,
    slug: String
) {
    navController.navigate("$slug/photos")
}