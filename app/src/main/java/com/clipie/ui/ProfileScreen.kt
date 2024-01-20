package com.clipie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.Black
import com.ramcosta.composedestinations.annotation.Destination


@Destination(start = false)
@Preview
@Composable
fun ProfileScreen() {


    Scaffold(
        topBar = {
            ProfileScreenTopBar()
        },
        bottomBar = {
            ProfileScreenBottomBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenBottomBar(
    modifier: Modifier = Modifier
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
                    IconButton(onClick = { selectedItem = index }) {
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
fun ProfileScreenTopBar() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    TopAppBar(title = {

        BadgedBox(badge = {
            if (TopListOfItems[3].hasNews) {
                Badge(Modifier.offset(x = (-9).dp, y = 35.dp)
                    .size(8.dp)
                )
            }
        }) {
            TextButton(onClick = { expanded = true }) {
                Text(
                    text = "User name here",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Icon(
                    imageVector = if (expanded) TopListOfItems[3].selectedIcon else TopListOfItems[3].unselectedIcon,
                    contentDescription = TopListOfItems[3].title,
                    modifier = Modifier.padding(start = 3.dp, top = 5.dp)
                )
            }
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier
                    .size(width = 180.dp, height = 40.dp),
                text = {
                    Text(
                        text = "TO DO!!!",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Icon(
                        imageVector = Icons.Outlined.Person, contentDescription = null,
                        Modifier.offset(130.dp, 3.dp)
                    )
                },
                onClick = { /*TODO*/ })

            DropdownMenuItem(
                modifier = Modifier
                    .size(width = 180.dp, height = 40.dp),
                text = {
                    Text(
                        text = "TO DO!!!",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Icon(
                        imageVector = Icons.Outlined.Star, contentDescription = null,
                        Modifier.offset(130.dp, 4.dp)
                    )
                },
                onClick = { /*TODO*/ })
        }
    },
        actions = {
            BadgedBox(badge = {
                if (TopListOfItems[4].hasNews) {
                    Badge(Modifier.offset(x = (-8).dp, y = 13.dp)
                        .size(8.dp)
                    )
                }
            }) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = TopListOfItems[4].unselectedIcon,
                        contentDescription = TopListOfItems[4].title,
                        Modifier.size(30.dp)
                    )
                }
            }
            BadgedBox(badge = {
                if (TopListOfItems[5].hasNews) {
                    Badge(Modifier.offset(x = (-9).dp, y = 13.dp)
                        .size(8.dp)
                    )
                }
            }) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = TopListOfItems[5].unselectedIcon,
                        contentDescription = TopListOfItems[5].title,
                        Modifier
                            .size(80.dp)
                            .offset((-9).dp, (0).dp)
                    )
                }
            }
        }
    )
}

