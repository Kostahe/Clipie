package com.clipie.presentation.main.search_screen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.clipie.ui.theme.ClipieTheme

@Composable
fun NestedSearchScreen() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NestedSearchScreenTopBar() {
    ClipieTheme {
        TopAppBar(title = {  })
    }
}