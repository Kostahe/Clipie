package com.clipie.main.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.clipie.main.presentation.chat.ChatScreen
import com.clipie.main.presentation.chat.ChatViewModel
import com.clipie.main.presentation.chat.navigation.ChatNavConstant
import com.clipie.main.presentation.clips.ClipsScreen
import com.clipie.main.presentation.home.HomeScreen
import com.clipie.main.presentation.profile.ProfileScreen
import com.clipie.main.presentation.search.SearchScreen
import com.clipie.main.presentation.upload.navigation.UploadNavConstant
import com.clipie.main.presentation.upload.screens.LivestreamScreen
import com.clipie.main.presentation.upload.screens.UploadClipScreen
import com.clipie.main.presentation.upload.screens.UploadPostScreen
import com.clipie.main.presentation.upload.screens.UploadStoryScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MainNavConstant.HomeNavigation.route,
        modifier = modifier
    ) {
        navigation(
            startDestination = MainNavConstant.Home.route,
            route = MainNavConstant.HomeNavigation.route
        ) {
            composable(MainNavConstant.Home.route) {
                HomeScreen()
            }
            composable(ChatNavConstant.Chat.route) {
                val viewModel = hiltViewModel<ChatViewModel>()

                ChatScreen(chatState = viewModel.state.collectAsState().value)
            }
        }
        composable(route = MainNavConstant.Search.route){
            SearchScreen()
        }
        navigation(
            startDestination = UploadNavConstant.UploadPost.route,
            route = MainNavConstant.Upload.route
        ) {
            composable(route = UploadNavConstant.UploadPost.route){
                UploadPostScreen(navController = navController)
            }
            composable(route = UploadNavConstant.UploadStory.route){
                UploadStoryScreen(navController = navController)
            }
            composable(route = UploadNavConstant.UploadClip.route){
                UploadClipScreen(navController = navController)
            }
            composable(route = UploadNavConstant.Livestream.route){
                LivestreamScreen(navController = navController)
            }
        }
        composable(route = MainNavConstant.Clips.route){
            ClipsScreen()
        }
        composable(route = MainNavConstant.Profile.route) {
            ProfileScreen()
        }
    }
}