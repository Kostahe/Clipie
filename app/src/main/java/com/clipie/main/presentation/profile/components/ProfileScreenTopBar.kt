package com.clipie.main.presentation.profile.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipie.authentication.domain.models.User
import com.clipie.authentication.presentation.AuthenticationViewModel
import com.clipie.main.presentation.home.components.TopListOfItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenTopBar() {
    val userList: List<User> = listOf(
        User(
            username = "Wcman007",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2FsN2KCsaq2KYKLQe9o1-jYQ-O_LxJQthXg&usqp=CAU"
        )
    )
    var accountSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    var createSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    var listsSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    val viewModel = hiltViewModel<AuthenticationViewModel>()
    TopAppBar(title = {
        BadgedBox(badge = {
            if (TopListOfItems[3].hasNews) {
                Badge(
                    Modifier
                        .offset(x = (-9).dp, y = 35.dp)
                        .size(8.dp)
                )
            }
        }) {
            TextButton(
                onClick = { accountSheetOpenSwitch = true }, modifier = Modifier
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f, false)
                        .padding(1.dp),
                    text = viewModel.getSession()?.username ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Icon(
                    imageVector = if (accountSheetOpenSwitch) TopListOfItems[3].selectedIcon else TopListOfItems[3].unselectedIcon,
                    contentDescription = TopListOfItems[3].title,
                    modifier = Modifier.padding(start = 0.dp, top = 5.dp)
                )
            }
        }
        if (accountSheetOpenSwitch) {
            AccountBottomSheet(
                onDismissRequest = { accountSheetOpenSwitch = false },
                profilesList = userList,
                onItemClick = { },
                onAddAccountClick = { }
            )
        }
        if (createSheetOpenSwitch) {
            CreateBottomSheet(
                onDismissRequest = { createSheetOpenSwitch = false },
                onItemClick = { }
            )
        }
        if (listsSheetOpenSwitch) {
            ListBottomSheet(
                onDismissRequest = { listsSheetOpenSwitch = false },
                onItemClick = { }
            )
        }
    }, actions = {
        BadgedBox(badge = {
            if (TopListOfItems[4].hasNews) {
                Badge(
                    Modifier
                        .offset(x = (-8).dp, y = 13.dp)
                        .size(8.dp)
                )
            }
        }) {
            IconButton(onClick = { createSheetOpenSwitch = true }) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null,
                    Modifier.size(30.dp)
                )
            }
        }

        BadgedBox(badge = {
            if (TopListOfItems[5].hasNews) {
                Badge(
                    Modifier
                        .offset(x = (-9).dp, y = 13.dp)
                        .size(8.dp)
                )
            }
        }) {
            IconButton(onClick = { listsSheetOpenSwitch = true }) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null,
                    Modifier
                        .size(40.dp)
                )
            }
        }
    })
}