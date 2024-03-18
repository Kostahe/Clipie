package com.clipie.main.presentation.upload.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clipie.main.presentation.navigation.MainNavConstant
import com.clipie.main.presentation.upload.components.BackHomeButton
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadPostScreen(navController: NavController) {
    TopAppBar(title = {
        Text(text = "New Post", fontWeight = FontWeight.Bold)
    },
        navigationIcon = { BackHomeButton(navController = navController) },
        actions = {
            TextButton(onClick = { /*TODO Move to the next screen*/ }) {
                Text(text = "Next", modifier = Modifier, fontSize = 25.sp)
            }
        })
}