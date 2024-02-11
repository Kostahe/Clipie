package com.clipie.presentation.main.clips_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClipsScreen() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClipsTopAppBar(title: String, backgroundColor: Color = Color.Transparent) {
    TopAppBar(
        title = { Text(text = "Clips") },
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(backgroundColor)
    )
}