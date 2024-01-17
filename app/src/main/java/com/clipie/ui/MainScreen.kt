package com.clipie.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                }
            }
        }
    ) {
        Text(text = "Hello", modifier = Modifier.padding(it))
    }
}
