package com.clipie.main.presentation.chat.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.clipie.R
import com.clipie.main.presentation.components.IconBack

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CreationChatTopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = { IconBack(navController = navController) },
        title = { Text(text = stringResource(R.string.create_chat)) }
    )
}