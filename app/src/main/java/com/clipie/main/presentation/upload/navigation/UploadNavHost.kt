package com.clipie.main.presentation.upload.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun UploadNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController,
        startDestination = UploadNavConstant.Post.route,
        modifier = modifier
    ){
        composable(route = UploadNavConstant.Post.route){

        }
        composable(route = UploadNavConstant.Story.route){

        }
        composable(route = UploadNavConstant.Clip.route){

        }
        composable(route = UploadNavConstant.Live.route){

        }

    }

}