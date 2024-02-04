package com.clipie.presentation.main.home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.clipie.presentation.main.TopListOfItems
import kotlin.reflect.KClass
import com.clipie.presentation.main.TopNavigationItem

val TopListOfItems: List<TopNavigationItem> = listOf(
    TopNavigationItem(
        "ForYouDropDownMenu", Icons.Outlined.KeyboardArrowDown, Icons.Outlined.KeyboardArrowUp, true
    ),
    TopNavigationItem(
        "Notifications", Icons.Outlined.FavoriteBorder, Icons.Outlined.FavoriteBorder, true
    ),
    TopNavigationItem(
        "Send", Icons.AutoMirrored.Filled.Send, Icons.AutoMirrored.Outlined.Send, true
    ),
    TopNavigationItem(
        "Profiles", Icons.Outlined.KeyboardArrowDown, Icons.Outlined.KeyboardArrowUp, true
    ),
    TopNavigationItem("Create", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, true),
    TopNavigationItem(null, Icons.AutoMirrored.Outlined.List, Icons.AutoMirrored.Filled.List, true)
)


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenTopBar() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val followNotification = false
    val favoritesNotification = false
    TopAppBar(title = {
//      For You Dropdownbutton
        BadgedBox(badge = {
            if (favoritesNotification || followNotification) {
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

            ForYouDropdownMenuItem(
                text = "Follow",
                icon = Icons.Outlined.Person,
                onClick = { /*TODO*/ },
                hasNotification = followNotification,
                contentDescription = null
            )
            ForYouDropdownMenuItem(
                text = "Favorites",
                icon = Icons.Filled.Star,
                onClick = { /*TODO*/ },
                hasNotification = favoritesNotification,
                contentDescription = null
            )

        }


    }, actions = {

//        Favorites IconButton

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

//        Messages IconButton

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForYouDropdownMenuItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    hasNotification: Boolean,
    contentDescription: String?
) {
    DropdownMenuItem(modifier = Modifier.size(width = 180.dp, height = 40.dp), text = {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            BadgedBox(badge = {
                if (hasNotification) {
                    Badge(Modifier.size(9.dp)) {

                    }
                }
            }, modifier = Modifier.padding(top = 8.dp)) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
            }
        }
    }, onClick = { onClick })
}