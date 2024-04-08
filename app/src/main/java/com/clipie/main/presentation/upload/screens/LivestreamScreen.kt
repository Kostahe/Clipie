package com.clipie.main.presentation.upload.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.clipie.main.presentation.upload.components.BackHomeButton

@Composable
fun LivestreamScreen(navController: NavController) {

    Row {
        BackHomeButton(navController = navController)
        Text(text = "Start Livestream")
    }
}