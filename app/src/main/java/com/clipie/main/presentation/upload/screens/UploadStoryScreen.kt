package com.clipie.main.presentation.upload.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.clipie.main.presentation.upload.components.BackHomeButton

@Composable
fun UploadStoryScreen(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        BackHomeButton(navController = navController)
        Text(text = "New Story", modifier = Modifier.weight(1f))
    }

}