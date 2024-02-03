package com.clipie.presentation.main.home_screen.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clipie.presentation.main.main_screen.components.TopListOfItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar() {
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