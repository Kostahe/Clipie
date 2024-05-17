@file:Suppress("NAME_SHADOWING")

package com.clipie.main.presentation.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.clipie.R
import com.clipie.authentication.domain.models.User
import com.clipie.util.PreviewParameterProvider
import com.clipie.util.Resource


@Composable
fun CreationChatScreen(
    modifier: Modifier = Modifier,
    onSearchTextChange: (String) -> Unit,
    userListState: Resource<List<User>>
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val selectedUserList = remember { mutableStateListOf<User>() }
    Column(
        modifier
            .padding(horizontal = 10.dp)
            .fillMaxHeight()
    ) {
        Text(text = stringResource(R.string.with))
        LazyRow(modifier = Modifier.height(32.dp)) {
            items(selectedUserList) { user ->
                var isVisible by remember { mutableStateOf(false) }

                LaunchedEffect(key1 = user) {
                    isVisible = true
                }

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(tween(1250))
                ) {
                    UserCard(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        user = user
                    )
                }
            }
        }
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchTextChange(it)
            },
            label = { Text(stringResource(R.string.search)) },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                errorIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.surface
            )
        )
        when (userListState) {
            is Resource.Error -> {
                Text("Error Screen")
            }

            is Resource.Loading -> {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                val userList = userListState.data
                userList?.let { userList ->
                    LazyColumn(
                        Modifier
                            .height(300.dp)
                            .weight(1f)
                    ) {
                        items(userList) { user ->
                            UserItem(
                                user = user,
                                onClick = { user, selected ->
                                    if (selected) {
                                        selectedUserList.add(user)
                                    } else {
                                        selectedUserList.remove(user)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .widthIn(max = 300.dp),
            onClick = { },
            enabled = selectedUserList.isNotEmpty(),
            shape = RoundedCornerShape(25)
        ) {
            Text(
                text = if (selectedUserList.size < 2)
                    stringResource(id = R.string.create_chat)
                else
                    stringResource(R.string.create_group_chat),
            )
        }
    }
}


@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onClick: (User, Boolean) -> Unit,
) {
    var selected by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                selected = !selected
                onClick(user, selected)
            },
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(modifier = modifier.weight(1f)) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(50.dp)),
                model = user.imageUrl,
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.temp_acc_pfp)
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = user.username
        )
        RadioButton(
            selected = selected,
            onClick = null
        )
    }
}

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    user: User
) {
    Card(
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(50),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

    ) {
        Text(
            text = user.username,
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreationChatScreenPreview() {
    CreationChatScreen(
        userListState = Resource.Success(PreviewParameterProvider.userList),
        onSearchTextChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(
        user = PreviewParameterProvider.userList.first(),
        onClick = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    UserCard(user = PreviewParameterProvider.userList.first())
}