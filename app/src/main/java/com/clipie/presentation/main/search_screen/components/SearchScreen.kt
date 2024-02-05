package com.clipie.presentation.main.search_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.ClipieTheme

@Composable
fun SearchScreen() {
    Text(text = "Random unordered content")

}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun SearchScreenTopBar() {
    val customTextFieldColors = TextFieldDefaults.colors(
        unfocusedIndicatorColor = Color.Transparent
    )
    ClipieTheme {
        TopAppBar(title = {
            var searchText by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = searchText,
                enabled = false,
                onValueChange = { searchText = it; },
                label = { Text(text = "Search") },
                colors = customTextFieldColors,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-4).dp)
                    .clickable { },
            )


        }, navigationIcon = {},
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreHoriz,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
        )
    }
}
