package com.clipie.main.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clipie.main.presentation.clips.ClipsScreen
import com.clipie.main.presentation.home.HomeScreen
import com.clipie.main.presentation.profile.ProfileScreen
import com.clipie.main.presentation.search.SearchScreen
import com.clipie.main.presentation.upload.UploadScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MainNavConstant.Home.route,
        modifier = modifier
    ) {
        composable(route = MainNavConstant.Home.route) {
            HomeScreen()
        }
        composable(route = MainNavConstant.Search.route){
            SearchScreen()
        }
        composable(route = MainNavConstant.Add.route){
            UploadScreen(navController)
        }
        composable(route = MainNavConstant.Clips.route){
            ClipsScreen()
        }
        composable(route = MainNavConstant.Profile.route) {
            ProfileScreen()
        }
    }
}