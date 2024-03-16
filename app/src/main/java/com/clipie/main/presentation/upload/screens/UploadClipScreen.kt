package com.clipie.main.presentation.upload.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.clipie.main.presentation.navigation.MainNavConstant
import com.clipie.main.presentation.upload.components.BackHomeButton

@Composable
fun UploadClipScreen(navController: NavController) {
   BackHomeButton(navController = navController)
}