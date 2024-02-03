package com.clipie.presentation.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clipie.presentation.main.add_screen.components.AddScreen
import com.clipie.presentation.main.clips_screen.components.ClipsScreen
import com.clipie.presentation.main.home_screen.components.HomeScreen
import com.clipie.presentation.main.profile_screen.components.ProfileScreen
import com.clipie.presentation.main.search_screen.components.SearchScreen

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
            AddScreen()
        }
        composable(route = MainNavConstant.Clips.route){
            ClipsScreen()
        }
        composable(route = MainNavConstant.Profile.route) {
            ProfileScreen()
        }
    }
}