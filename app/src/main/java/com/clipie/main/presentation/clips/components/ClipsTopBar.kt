package com.clipie.main.presentation.clips.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClipsTopBar(backgroundColor: Color = Color.Transparent) {
    TopAppBar(
        title = { Text(text = "Clips", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold) },
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