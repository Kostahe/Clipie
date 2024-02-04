package com.clipie.presentation.main.search_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.clipie.ui.theme.md_theme_dark_outline
import com.clipie.ui.theme.md_theme_light_outline

@Composable
fun SearchScreen() {


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenTopBar() {
    TopAppBar(title = {
        var searchText by rememberSaveable { mutableStateOf("") }

        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
        Row(
            modifier = Modifier.background(
                shape = RoundedCornerShape(50),
                color = if (isSystemInDarkTheme()) {
                    md_theme_dark_outline
                } else {
                    md_theme_light_outline
                }
            )
        ) {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text(text = "Search") },
                singleLine = true
            )
        }

//

    })
}