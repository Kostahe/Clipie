package com.clipie.presentation.profile_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateOf
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PlayArrow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clipie.R
import com.clipie.presentation.main_screen.components.TopListOfItems
import com.clipie.presentation.main_screen.components.listOfItems
import com.clipie.ui.theme.Black
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Preview
@Composable
fun ProfileScreen() {


    Scaffold(topBar = {
        ProfileScreenTopBar()
    }, bottomBar = {
        ProfileScreenBottomBar()
    }) { padding ->
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
    val accountSheetState = rememberModalBottomSheetState()
    var accountSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    val createSheetState = rememberModalBottomSheetState()
    var createSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
    val listsSheetState = rememberModalBottomSheetState()
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
                    color = Black,
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

//--------------Selected account

                AccountItem("Franta", painterResource(id = R.drawable.temp_acc_pfp), true)


//--------------Unselected account

                AccountItem("Bob", painterResource(id = R.drawable.temp_acc_pfp), false)

//--------------Add account

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
                        text = "Add account",
                        Modifier.padding(start = 20.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }
        }


        if (createSheetOpenSwitch) {
//      Create ModalBottomSheet
            ModalBottomSheet(
                onDismissRequest = { createSheetOpenSwitch = false },
                sheetState = createSheetState,
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Create",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider()
                CreateBottomSheetItem(text = "Clip", icon = Icons.Outlined.PlayArrow)
                CreateBottomSheetItem(text = "Post", icon = Icons.Outlined.PlayArrow)
                CreateBottomSheetItem(text = "Story", icon = Icons.Outlined.PlayArrow)
                CreateBottomSheetItem(text = "Story Highlight", icon = Icons.Outlined.PlayArrow)

            }
        }

        if (listsSheetOpenSwitch) {
            ModalBottomSheet(
                onDismissRequest = { listsSheetOpenSwitch = false },
                sheetState = listsSheetState
            ) {

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
fun CreateBottomSheetItem(text: String, icon: ImageVector, contentDescription: String? = null) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {}.fillMaxWidth()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp)) {
            Icon(
                imageVector = icon, contentDescription = contentDescription,
                modifier = Modifier.size(35.dp)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(vertical = 10.dp)
        )
    }
    Divider(
        modifier = Modifier
            .offset(42.dp)
    )

}
