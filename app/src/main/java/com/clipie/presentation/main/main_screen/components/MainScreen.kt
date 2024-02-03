package com.clipie.presentation.main.main_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.clipie.presentation.navigation.main.MainNavConstant

val TopListOfItems: List<TopNavigationItem> = listOf(
    TopNavigationItem(
        "ForYouDropDownMenu", Icons.Outlined.KeyboardArrowDown, Icons.Outlined.KeyboardArrowUp, true
    ),
    TopNavigationItem(
        "Notifications", Icons.Outlined.FavoriteBorder, Icons.Outlined.FavoriteBorder, true
    ),
    TopNavigationItem(
        "Send",
        Icons.AutoMirrored.Filled.Send,
        Icons.AutoMirrored.Outlined.Send,
        true
    ),
    TopNavigationItem(
        "Profiles", Icons.Outlined.KeyboardArrowDown, Icons.Outlined.KeyboardArrowUp, true
    ),
    TopNavigationItem("Create", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, true),
    TopNavigationItem(null, Icons.AutoMirrored.Outlined.List, Icons.AutoMirrored.Filled.List, true)
)

val listOfItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, true, MainNavConstant.Home.route),
    BottomNavigationItem("Search", Icons.Filled.Search, Icons.Outlined.Search, false, MainNavConstant.Search.route),
    BottomNavigationItem("Add", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, false, MainNavConstant.Add.route),
    BottomNavigationItem("Clips", Icons.Filled.PlayArrow, Icons.Outlined.PlayArrow, false, MainNavConstant.Clips.route),
    BottomNavigationItem("Profile", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, false, MainNavConstant.Profile.route)
)

@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(topBar = {
        MainScreenTopBar()
    }, bottomBar = {
        MainScreenBottomBar(navController = navController, modifier = Modifier)
    }) { padding ->
        Text(text = "", modifier = Modifier.padding(padding))

    }
}

data class TopNavigationItem(
    val title: String?,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenBottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    BottomAppBar {
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOfItems.forEachIndexed { index, item ->
                BadgedBox(badge = {
                    if (item.hasNews) Badge()
                }) {
                    IconButton(onClick = { selectedItem = index; navController.navigate(listOfItems[index].route) }) {
                        Icon(
                            imageVector = if (selectedItem == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    TopAppBar(title = {

        BadgedBox(badge = {
            if (TopListOfItems[0].hasNews) {
                Badge(
                    Modifier
                        .offset(x = (-9).dp, y = 35.dp)
                        .size(8.dp)
                )
            }
        }) {
            TextButton(onClick = { expanded = true }) {
                Text(
                    text = "For you",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Icon(
                    imageVector = if (expanded) TopListOfItems[0].selectedIcon else TopListOfItems[0].unselectedIcon,
                    contentDescription = TopListOfItems[0].title,
                    modifier = Modifier.padding(start = 3.dp, top = 5.dp)
                )
            }
        }



        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(modifier = Modifier.size(width = 180.dp, height = 40.dp), text = {
                Text(
                    text = "Following",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    Modifier.offset(130.dp, 3.dp)
                )
            }, onClick = { /*TODO*/ })

            DropdownMenuItem(modifier = Modifier.size(width = 180.dp, height = 40.dp), text = {
                Text(
                    text = "Favorites", style = MaterialTheme.typography.headlineSmall
                )
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    Modifier.offset(130.dp, 4.dp)
                )
            }, onClick = { /*TODO*/ })
        }


    }, actions = {
        BadgedBox(badge = {
            if (TopListOfItems[1].hasNews) {
                Badge(
                    Modifier
                        .offset(x = (-8).dp, y = 13.dp)
                        .size(8.dp)
                )
            }
        }) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = TopListOfItems[1].unselectedIcon,
                    contentDescription = TopListOfItems[1].title,
                    Modifier.size(30.dp)
                )
            }
        }
        BadgedBox(badge = {
            if (TopListOfItems[2].hasNews) {
                Badge(
                    Modifier
                        .offset(x = (-9).dp, y = 13.dp)
                        .size(8.dp)
                )
            }
        }) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = TopListOfItems[2].unselectedIcon,
                    contentDescription = TopListOfItems[2].title,
                    Modifier
                        .rotate(330f)
                        .size(30.dp)
                        .offset(0.dp, (-4).dp)
                )
            }
        }
    })
}

