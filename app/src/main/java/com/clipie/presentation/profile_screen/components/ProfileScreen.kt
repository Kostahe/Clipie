package com.clipie.presentation.profile_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateOf
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.clipie.R
import com.clipie.presentation.main_screen.components.TopListOfItems
import com.clipie.presentation.main_screen.components.listOfItems
import com.clipie.ui.theme.ButtonBackground
import com.clipie.ui.theme.ClipieTheme
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
@Preview
@Composable
fun ProfileScreen() {

    Scaffold(topBar = {
        ProfileScreenTopBar()
    }, bottomBar = {
        ProfileScreenBottomBar()
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenBottomBar(
    modifier: Modifier = Modifier
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
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
    val accountSheetState = rememberModalBottomSheetState(false)
    var accountSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    val createSheetState = rememberModalBottomSheetState(true)
    var createSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    val listsSheetState = rememberModalBottomSheetState(true)
    var listsSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
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
                    text = "Insert username here",
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

            ModalBottomSheet(
                onDismissRequest = { accountSheetOpenSwitch = false },
                sheetState = accountSheetState,
            ) {
                AccountItem("Franta", painterResource(id = R.drawable.temp_acc_pfp), true)
                AccountItem("Bob", painterResource(id = R.drawable.temp_acc_pfp), false)

                Row(modifier = Modifier
                    .clickable {
                        Log.d(
                            "currentProfileRow", "Current account has been clicked!!!"
                        )
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f),
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape)
                    )
                    Text(
                        text = stringResource(R.string.add_account),
                        Modifier.padding(start = 20.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }
        }


        if (createSheetOpenSwitch) {
            ModalBottomSheet(
                onDismissRequest = { createSheetOpenSwitch = false },
                sheetState = createSheetState,
                modifier = Modifier.height(550.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.create),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                CreateBottomSheetItem(
                    text = stringResource(R.string.clip),
                    icon = Icons.Outlined.PlayArrow
                ) {}
                CreateBottomSheetItem(
                    text = stringResource(R.string.post),
                    icon = Icons.Outlined.PlayArrow
                ) {}
                CreateBottomSheetItem(
                    text = stringResource(R.string.story),
                    icon = Icons.Outlined.PlayArrow
                ) {}
                CreateBottomSheetItem(
                    text = stringResource(R.string.story_highlight),
                    icon = Icons.Outlined.PlayArrow
                ) {}
                CreateBottomSheetItem(
                    text = stringResource(R.string.live),
                    icon = Icons.Outlined.PlayArrow
                ) {}
                CreateBottomSheetItem(
                    text = stringResource(R.string.made_for_you),
                    icon = Icons.Outlined.PlayArrow
                ) {}
                CreateBottomSheetItem(
                    text = stringResource(R.string.fundraiser),
                    icon = Icons.Outlined.PlayArrow
                ) {}

            }
        }

        if (listsSheetOpenSwitch) {
            ModalBottomSheet(
                onDismissRequest = { listsSheetOpenSwitch = false },
                sheetState = listsSheetState,
                modifier = Modifier
            ) {
                ListsBottomSheetItem(
                    text = "Settings and Privacy",
                    icon = Icons.Outlined.Settings,
                    notificationCount = 69
                )
                ListsBottomSheetItem(
                    text = "Your Activity",
                    icon = Icons.Outlined.Info,
                    notificationCount = 420
                )
                ListsBottomSheetItem(
                    text = "Archived",
                    icon = Icons.Outlined.Star,
                    notificationCount = 0
                )
                ListsBottomSheetItem(
                    text = "Share Account",
                    icon = Icons.Outlined.Share,
                    notificationCount = 1
                )
                ListsBottomSheetItem(
                    text = "Saved",
                    icon = Icons.Outlined.Star,
                    notificationCount = 36
                )
                ListsBottomSheetItem(
                    text = "Supervision",
                    icon = Icons.Outlined.Face,
                    notificationCount = 0
                )
                ListsBottomSheetItem(
                    text = "Verification",
                    icon = Icons.Outlined.CheckCircle,
                    notificationCount = 0
                )
                ListsBottomSheetItem(
                    text = "Close Friends",
                    icon = Icons.Outlined.Person,
                    notificationCount = 0
                )
                ListsBottomSheetItem(
                    text = "Favorites",
                    icon = Icons.Outlined.Star,
                    notificationCount = 22
                )

            }
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
                    imageVector = TopListOfItems[4].unselectedIcon,
                    contentDescription = TopListOfItems[4].title,
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
                    imageVector = TopListOfItems[5].unselectedIcon,
                    contentDescription = TopListOfItems[5].title,
                    Modifier
                        .size(40.dp)
                )
            }
        }
    })
}

@Composable
fun AccountItem(accountName: String, pfp: Painter, selected: Boolean) {

    Row(modifier = Modifier
        .clickable {
            Log.d(
                "currentProfileRow", "Current account has been clicked!!!"
            )
        }
        .fillMaxWidth()
        .fillMaxHeight(0.1f), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(20.dp))
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = pfp,
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Text(
                text = accountName,
                Modifier.padding(start = 20.dp, top = 20.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
//                  TO DO: Don't forget to add functionality to RadioButtons so only one can be selected
        RadioButton(
            selected = selected, onClick = { }, modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun CreateBottomSheetItem(
    text: String,
    icon: ImageVector,
    iconContentDescription: String? = null,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable { onClick() }
        .fillMaxWidth()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp)) {
            Icon(
                imageVector = icon, contentDescription = iconContentDescription,
                modifier = Modifier.size(35.dp)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 9.dp)
        )
    }
    Divider(
        modifier = Modifier.offset(42.dp),
    )

}

@Composable
fun ListsBottomSheetItem(
    text: String,
    icon: ImageVector,
    notificationCount: Int,
    iconContentDescription: String? = null
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                Modifier.size(30.dp)
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 12.dp)
        ) {
            Text(
                text = notificationFormat(notificationCount),
                modifier = Modifier
                    .background(ButtonBackground, shape = RoundedCornerShape(100)),
                style = MaterialTheme.typography.labelLarge, color = Color.White
            )
        }

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