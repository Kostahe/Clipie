package com.clipie.presentation.main.search_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.ClipieTheme

@Composable
fun SearchScreen() {

    Text(text = "Content dependant on Filter suggestions")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenTopBar() {
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableStateListOf<String>() }
    var moreMenu by rememberSaveable { mutableStateOf(false) }
    var selectSwitch by rememberSaveable { mutableStateOf(false) }
    ClipieTheme {
        Row(modifier = Modifier.fillMaxWidth()) {

            SearchBar(
                modifier = Modifier.offset(y = (-5).dp),
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = {
                    active = false; if (searchText.isNotEmpty()) {
                    searchHistory.add(it)
                }
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }, trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (searchText.isNotEmpty()) {
                                    searchText = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            ) {
                LazyColumn {
                    items(searchHistory) { item ->
                        SearchHistoryItem(
                            it = item,
                            onDelete = { searchHistory.remove(item) },
                            onClick = { searchText = it },
                        )
                    }
                }
            }
            IconButton(
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = null, modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { moreMenu = true }
                )
                DropdownMenu(expanded = moreMenu,
                    onDismissRequest = { moreMenu = false }, modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Filter suggestions",
                        modifier = Modifier.padding(horizontal = 40.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    MoreDropdownMenuItem(
                        "For you",
                        icon = Icons.Outlined.PeopleOutline,
                        selected = selectSwitch,
                        onClick = { selectSwitch = true },
                        null
                    )
                    MoreDropdownMenuItem(
                        "Not Personalised",
                        icon = Icons.Outlined.AccountCircle,
                        selected = !selectSwitch,
                        onClick = {
                            selectSwitch = false
                        },
                        null
                    )
                }
            }
        }
    }
}


@Composable
fun SearchHistoryItem(
    it: String,
    onDelete: () -> Unit,
    onClick: (String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(all = 14.dp)
        .clickable { onClick.invoke(it) }) {
        Icon(
            imageVector = Icons.Outlined.History,
            contentDescription = null,
            modifier = Modifier.padding(end = 15.dp)
        )
        Text(text = it)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Outlined.Close,
            contentDescription = null,
            modifier = Modifier.clickable { onDelete() }
        )
    }
}


@Composable
fun MoreDropdownMenuItem(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    contentDesc: String?
) {
    DropdownMenuItem(text = {
        Row(modifier = Modifier.height(50.dp)) {
            if (selected) {
                Box(modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(30.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            Spacer(modifier = Modifier)
            Icon(
                imageVector = icon,
                contentDescription = contentDesc,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Divider()
    }, onClick = { onClick() }, modifier = Modifier.requiredWidth(220.dp))
}