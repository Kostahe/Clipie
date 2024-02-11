package com.clipie.presentation.main.clips_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.ClipieTheme

@Composable
fun ClipsScreen() {
    var appBarColor by remember { mutableStateOf(Color(0, 0, 0, 100)) }
    ClipieTheme {
        ClipsTopAppBar(title = "Clips")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClipsTopAppBar(title: String, backgroundColor: Color = Color.Transparent) {
    TopAppBar(
        title = { Text(text = "Clips", style = MaterialTheme.typography.headlineMedium) },
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(backgroundColor),
        actions = {
            IconButton(onClick = { /*Move to Upload Clips*/ }, modifier = Modifier.size(40.dp)) {
                Icon(imageVector = Icons.Outlined.PhotoCamera, contentDescription = null, modifier = Modifier.size(40.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    )
}