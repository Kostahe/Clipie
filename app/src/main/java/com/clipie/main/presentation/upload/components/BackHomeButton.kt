package com.clipie.main.presentation.upload.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.clipie.main.presentation.navigation.MainNavConstant

@Composable
fun BackHomeButton(navController: NavController) {
    IconButton(onClick = {
        navController.navigate(MainNavConstant.Home.route)
    }) {
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
    }
}