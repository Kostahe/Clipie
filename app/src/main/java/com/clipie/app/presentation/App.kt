package com.clipie.app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.clipie.app.presentation.navigation.AppNavHost

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    AppNavHost(
        navController = navController,
        modifier = modifier
    )
}