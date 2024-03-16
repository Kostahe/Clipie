package com.clipie.main.presentation.add

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.clipie.main.presentation.add.camera.CameraPreview
import com.clipie.main.presentation.add.camera.CameraScreen

@Composable
fun AddScreen(){
    Text(text = "Navigate to NewPostScreen")
    IconButton(onClick = { /*TODO Navigate to HomeScreen*/ }) {
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
    }
//    CameraScreen()
}