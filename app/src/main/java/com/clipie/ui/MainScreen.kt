package com.clipie.ui

import android.text.method.Touch
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle

val listOfItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        true
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Preview
@Composable
fun MainScreen() {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                TextButton(onClick = { expanded = true }) {
                    Text(
                        text = "For you",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(
                        modifier = Modifier
                            .size(width = 150.dp, height = 70.dp),
                        text = {
                            Text(
                                text = "Following",
                                style = MaterialTheme.typography.headlineMedium,
                            )
                        },
                        onClick = { /*TODO*/ })

                    DropdownMenuItem(
                        modifier = Modifier
                            .size(width = 150.dp, height = 70.dp),
                        text = {
                            Text(
                                text = "Favorites",
                                style = MaterialTheme.typography.headlineMedium
                            )
                        },
                        onClick = { /*TODO*/ })

                }
            },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Send, contentDescription = null)
                    }

                }
            )
        },
        bottomBar = {
            NavigationBar() {
                listOfItems.forEach { _ ->
                    NavigationBarItem(selected = false, onClick = { }, icon = { })
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