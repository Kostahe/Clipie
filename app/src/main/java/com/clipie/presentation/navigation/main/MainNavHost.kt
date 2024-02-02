package com.clipie.presentation.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clipie.presentation.home_screen.components.HomeScreen
import com.clipie.presentation.profile_screen.components.ProfileScreen

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
        composable(route = MainNavConstant.Profile.route) {
            ProfileScreen()
        }
    }
}