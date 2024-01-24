package com.clipie.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clipie.R
import com.clipie.ui.theme.Black
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch


@Destination(start = true)
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

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    var edgeToEdgeEnabled by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

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
            TextButton(onClick = {
                Log.d("test", "1 TEST!!!!!!!")
                scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                    if (!bottomSheetState.isVisible) {
                        openBottomSheet = false
                    }
                }

                Log.d("test", "2 TEST!!!!!!!")
            }
            ) {
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
        data class account(
            val pfp: Painter,
            val name: String,
            val notificationCount: Int,
        ) {}

        val imagePainter: Painter = painterResource(id = R.drawable.temp_acc_pfp)

        val userAccountList: List<account> = listOf(
            account(
                imagePainter,
                "franta",
                69
            )
        )
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
            windowInsets = windowInsets

        ) {
            if(openBottomSheet){
                val windowInsets = if (edgeToEdgeEnabled)
                    WindowInsets(0) else BottomSheetDefaults.windowInsets
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                horizontalAlignment = Alignment.Start,

                ) {
//                Selected profile
                item {
                    Button(onClick = { /*TODO*/ }) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = userAccountList[0].pfp,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                            )
                            Text(text = userAccountList[0].name)
                            Text(text = userAccountList[0].notificationCount.toString() + "Notifications")

                        }
                    }

                }
//                All unSelected profiles
//                if (Profile.Count > 1){
//                    display other profiles
//                }
                item {
                    Button(onClick = { /*TODO*/ }) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                            Text(text = "Add Account")
                            Text(text = userAccountList[0].notificationCount.toString() + "Notifications")
                        }
                    }


                }

//                    Add Account

                item() {
                    OutlinedButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                2.dp, Color.White, RoundedCornerShape(50.dp)

                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.size(59.dp)
                            )
                            Text(text = "Add Account")
                            Text(text = userAccountList[0].notificationCount.toString() + "Notifications")
                        }
                    }
                }
            }
        }
    },
        actions = {
            BadgedBox(badge = {
                if (TopListOfItems[4].hasNews) {
                    Badge(
                        Modifier
                            .offset(x = (-8).dp, y = 13.dp)
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
                    Badge(
                        Modifier
                            .offset(x = (-9).dp, y = 13.dp)
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

