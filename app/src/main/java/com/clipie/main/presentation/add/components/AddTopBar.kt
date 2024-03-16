package com.clipie.main.presentation.add.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.clipie.main.presentation.navigation.MainNavConstant

@Composable
fun AddTopBar(navController: NavHostController) {
    IconButton(onClick = {
        navController.navigate(MainNavConstant.Home.route)
    }) {
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
    }
}