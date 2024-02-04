package com.clipie.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.clipie.presentation.navigation.app.AppNavHost

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