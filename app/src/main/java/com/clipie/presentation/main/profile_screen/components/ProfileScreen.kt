package com.clipie.presentation.main.profile_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.clipie.R
import com.clipie.domain.model.User
import com.clipie.presentation.main.home_screen.components.TopListOfItems
import com.clipie.ui.theme.ClipieTheme

@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
@Preview
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()) {
        ProfileScreenInformation(user = User())
        UserPanel()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenTopBar() {
    val userList: List<User> = listOf(User(username = "Wcman007", imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2FsN2KCsaq2KYKLQe9o1-jYQ-O_LxJQthXg&usqp=CAU"))
    var accountSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    var createSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    var listsSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    ClipieTheme {
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
                            .offset(x = (-5).dp)
                            .weight(1f, false)
                            .padding(1.dp),
                        text = stringResource(R.string.insert_username_here),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Icon(
                        imageVector = if (accountSheetOpenSwitch) TopListOfItems[3].selectedIcon else TopListOfItems[3].unselectedIcon,
                        contentDescription = TopListOfItems[3].title,
                        modifier = Modifier.padding(start = 20.dp, top = 5.dp)
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
}

fun notificationFormat(notificationCount: Int): String {
    val outPut: String = when (notificationCount) {
        0 -> ""
        in 1..99 -> "  $notificationCount  "
        in 99..Int.MAX_VALUE -> "  99 + "
        else -> " Unknown "
    }
    return outPut
}