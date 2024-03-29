package com.clipie.main.presentation.upload.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.clipie.camera.CameraScreen
import com.clipie.main.presentation.upload.components.BackHomeButton

@Composable
fun LivestreamScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()){
        CameraScreen(modifier = Modifier)
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween, ) {
            BackHomeButton(navController = navController)

            Text(text = "<Active follower count>")

            IconButton(onClick = { /*TODO*/ }) {

                Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
            }
        }
    }
}