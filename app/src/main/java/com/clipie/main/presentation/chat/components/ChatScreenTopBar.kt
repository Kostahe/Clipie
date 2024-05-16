package com.clipie.main.presentation.chat.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.clipie.R
import com.clipie.main.presentation.components.IconBack
import com.clipie.main.presentation.home.components.TopListOfItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenTopBar(
    modifier: Modifier = Modifier,
    userName: String,
    navController: NavHostController
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    TopAppBar(
        modifier = modifier,
        title = {
            TextButton(onClick = { expanded = !expanded }) {
                Text(
                    text = userName,
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
        },
        navigationIcon = { IconBack(navController = navController) },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.new_message)
                )
            }
        }
    )
}

