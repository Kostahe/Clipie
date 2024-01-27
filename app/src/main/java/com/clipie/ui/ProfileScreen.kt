package com.clipie.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clipie.R
import com.clipie.ui.theme.Black
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@Destination
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
    val accountSheetState = rememberModalBottomSheetState()
    var accountSheetOpenSwitch by rememberSaveable { mutableStateOf(false) }
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
            TextButton(onClick = { accountSheetOpenSwitch = true }, modifier = Modifier
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
        data class Account(
            val pfp: Painter,
            val name: String,
            val notificationCount: Int,
        )

        val imagePainter: Painter = painterResource(id = R.drawable.temp_acc_pfp)

        val userAccountList: List<Account> = listOf(
            Account(
                imagePainter,
                "Franta",
                69
            ),
            Account(
                painterResource(id = R.drawable.ic_launcher_background),
                "vlad",
                2
            )
        )
        if (accountSheetOpenSwitch) {


            ModalBottomSheet(
                onDismissRequest = { accountSheetOpenSwitch = false },
                sheetState = accountSheetState,
            ) {
//                Selected account
                Row(modifier = Modifier
                    .clickable { Log.d("rowclick", "Row has been clicked!") }
                    .fillMaxWidth()
                    .offset()
                    .fillMaxHeight(0.1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape)
                    )
                    Text(
                        text = userAccountList[0].name,
                        modifier = Modifier.offset(x = 20.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
//                Column of unselected accounts



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
