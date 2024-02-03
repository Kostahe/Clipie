package com.clipie.presentation.navigation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.clipie.presentation.auth.AuthenticationViewModel
import com.clipie.presentation.auth.login_screen.components.LoginScreen
import com.clipie.presentation.auth.registration_screen.components.RegistrationScreen
import com.clipie.presentation.main.add_screen.components.AddScreen
import com.clipie.presentation.main.home_screen.components.HomeScreen
import com.clipie.presentation.main.main_screen.components.MainScreen
import com.clipie.presentation.main.profile_screen.components.ProfileScreen
import com.clipie.presentation.main.search_screen.components.SearchScreen
import com.clipie.presentation.navigation.main.MainNavConstant
import com.clipie.presentation.navigation.main.MainNavHost

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
                val viewModel = it.sharedViewModel<AuthenticationViewModel>(navController = navController)
                LoginScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = AuthenticationNavConstant.Register.route) {
                val viewModel = it.sharedViewModel<AuthenticationViewModel>(navController = navController)
                RegistrationScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = AppNavConstant.Main.route) {
                MainScreen()
            }
            composable(route = MainNavConstant.Home.route){
                HomeScreen()
            }
            composable(route = MainNavConstant.Profile.route){
                ProfileScreen()
            }
            composable(route = MainNavConstant.Search.route){
                SearchScreen()
            }
            composable(route = MainNavConstant.Add.route){
                AddScreen()
            }
        }

    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}