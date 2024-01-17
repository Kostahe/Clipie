package com.clipie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination

val listOfItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        true
    )
)

@Destination(start = true)
@Preview
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            NavigationBar() {
                listOfItems.forEach { _ ->
                    NavigationBarItem(selected = false, onClick = {  }, icon = { })
                }
            }
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(padding)
        ) {
            Text(text = "Hi")
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

//NavigationBarItem(selected = , onClick = { /*TODO*/ }, icon = { /*TODO*/ })
//IconButton(onClick = {  }) {
//    Icon(imageVector = Icons.Default.Home, contentDescription = null)
//}
//IconButton(onClick = { /*TODO*/ }) {
//    Icon(imageVector = Icons.Default.Search, contentDescription = null)
//}
//IconButton(onClick = { /*TODO*/ }) {
//    Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
//}
//IconButton(onClick = { /*TODO*/ }) {
//    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
//}