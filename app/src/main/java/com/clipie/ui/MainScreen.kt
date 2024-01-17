package com.clipie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

val listOfItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        "Home", Icons.Filled.Home, Icons.Outlined.Home, true
    ),
    BottomNavigationItem("Search", Icons.Filled.Search, Icons.Outlined.Search, false),
    BottomNavigationItem("Add", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, false),
    BottomNavigationItem("Profile", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, false)
)

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Preview
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            var selectedItem by rememberSaveable {
                mutableIntStateOf(0)
            }

            NavigationBar() {
                listOfItems.forEachIndexed { index, item ->
                    NavigationBarItem(selected = index == selectedItem,
                        onClick = { selectedItem = index },
                        icon = {
                            BadgedBox(badge = {
                                if (item.hasNews) Badge()
                            }) {
                                if (index == (listOfItems.size /2)) {
                                    FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(4.dp)) {
                                    }
                                }
                                Icon(
                                    imageVector = if (selectedItem == index) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        })
                }


            }
        }

        ) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
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

@Composable
fun MainScreenBottomBar(
    modifier: Modifier = Modifier
) {

    BottomAppBar {
        Row(modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }

    }
}

