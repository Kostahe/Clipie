package com.clipie.presentation.main.search_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.ClipieTheme

@Composable
fun SearchScreen() {

    Text(text = "Random unordered content")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenTopBar() {
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableStateListOf<String>() }
    ClipieTheme {
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
                items(searchHistory) {
                    SearchHistoryItem(
                        searchHistoryList = searchHistory,
                        it = it,
                        searchText = searchText
                    )
                }
            }
        }
    }
}


@Composable
fun SearchHistoryItem(
    searchHistoryList: SnapshotStateList<String>,
    it: String,
    searchText: String
) {
    var searchText = searchText
    Row(modifier = Modifier
        .padding(all = 14.dp)
        .clickable { searchText = it }) {

        Icon(
            imageVector = Icons.Outlined.History,
            contentDescription = null,
            modifier = Modifier.padding(end = 15.dp)
        )

        Text(text = it)

        Spacer(modifier = Modifier.weight(1f))

        Icon(imageVector = Icons.Outlined.Close,
            contentDescription = null,
            modifier = Modifier.clickable { searchHistoryList.remove(it) }
        )
    }
}