package com.clipie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Send
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.Black
import com.ramcosta.composedestinations.annotation.Destination

val listOfItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, true),
    BottomNavigationItem("Search", Icons.Filled.Search, Icons.Outlined.Search, false),
    BottomNavigationItem("Add", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, false),
    BottomNavigationItem("Profile", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, false)
)

//@Destination(start = true)
@Preview
@Composable
fun MainScreen() {


    Scaffold(
        topBar = {
            MainScreenTopBar()
        },
        bottomBar = {
            MainScreenBottomBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenBottomBar(
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
fun MainScreenTopBar() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    TopAppBar(title = {
        TextButton(onClick = { expanded = true }) {
            Text(
                text = "For you",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Black
            )
            Icon(
                imageVector = if (expanded) Icons.Outlined.KeyboardArrowDown else Icons.Outlined.KeyboardArrowUp,
                contentDescription = null,
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier
                    .size(width = 180.dp, height = 40.dp),
                text = {
                    Text(
                        text = "Following",
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
                        text = "Favorites",
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Send, contentDescription = null, Modifier
                        .rotate(325f)
                        .size(34.dp)
                        .offset(0.dp, (-4).dp)
                )
            }
        }
    )
}

