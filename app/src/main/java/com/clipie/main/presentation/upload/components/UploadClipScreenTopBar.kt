package com.clipie.main.presentation.upload.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.clipie.main.presentation.upload.navigation.UploadNavConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadClipScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "New Clip",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        },
        navigationIcon = { BackHomeButton(navController = navController) },
        actions = {
            IconButton(onClick = { navController.navigate(UploadNavConstant.Livestream.route) }) {
                Icon(imageVector = Icons.Outlined.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(29.dp))
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
