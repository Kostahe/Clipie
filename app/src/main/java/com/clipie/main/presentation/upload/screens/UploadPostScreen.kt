package com.clipie.main.presentation.upload.screens

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.navigation.NavController

@Composable
fun UploadPostScreen(navController: NavController) {

    Text(text = "Upload Post Screen")

        LazyRow() {
            items(20) { index ->
                Text(text = "test$index")
            }

        }




}
