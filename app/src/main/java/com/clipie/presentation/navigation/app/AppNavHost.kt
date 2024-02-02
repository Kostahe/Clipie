package com.clipie.presentation.navigation.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.clipie.presentation.auth.login_screen.components.LoginScreen
import com.clipie.presentation.auth.registration_screen.components.RegistrationScreen
import com.clipie.presentation.main.main_screen.components.MainScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppNavConstant.Authentication.route,
        modifier = modifier
    ) {
        navigation(
            startDestination = AuthenticationNavConstant.Login.route,
            route = AppNavConstant.Authentication.route
        ) {
            composable(route = AuthenticationNavConstant.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(route = AuthenticationNavConstant.Register.route) {
                RegistrationScreen(navController = navController)
            }
        }
        composable(route = AppNavConstant.Main.route) {
            MainScreen()
        }
    }
}