package com.clipie.main.presentation.upload.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clipie.main.presentation.navigation.MainNavConstant
import com.clipie.main.presentation.upload.components.BackHomeButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadClipScreen(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "New Clip", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        },
        navigationIcon = { BackHomeButton(navController = navController) },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
               Icon(imageVector = Icons.Outlined.Settings, contentDescription = null, modifier = Modifier.size(29.dp))
            }
        },
        colors = TopAppBarColors(
            Color.Transparent,
            Color.Transparent,
            Color.Unspecified,
            Color.Unspecified,
            Color.Unspecified
        )
    )


}