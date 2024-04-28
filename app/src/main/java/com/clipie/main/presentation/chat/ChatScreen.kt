@file:Suppress("NAME_SHADOWING")

package com.clipie.main.presentation.chat

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.clipie.R
import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat
import com.clipie.util.PreviewParameterProvider
import com.clipie.util.Resource


@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    chatState: Resource<ChatState>
) {
    AnimatedContent(
        targetState = chatState, modifier = modifier
    ) { chatState ->
        when (chatState) {
            is Resource.Error -> TODO()
            is Resource.Loading -> TODO()
            is Resource.Success -> chatState.data?.let { chatState ->
                ChatSuccessScreen(
                    chatState = chatState
                )
            }
        }
    }
}

@Composable
fun ChatSuccessScreen(
    modifier: Modifier = Modifier,
    chatState: ChatState
) {
    val chatList = chatState.chatList
    val currentUser = chatState.currentUser
    LazyColumn(modifier = modifier) {
        items(chatList) { chat ->
            ChatItem(
                currentUser = currentUser,
                chat = chat,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}


@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    currentUser: User,
    chat: Chat,
) {
    val usersList = chat.userList.toMutableList()
    usersList.remove(currentUser)
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(modifier = Modifier.weight(1f)) {
            if (usersList.count() < 3) {
                AsyncImage(
                    model = usersList.first().imageUrl,
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.temp_acc_pfp),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

            } else {
                AsyncImage(
                    model = usersList.first().imageUrl,
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.temp_acc_pfp),
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
            }
        }
        Text(
            modifier = Modifier.weight(1f),
            text = chat.name
        )
        Box(modifier.weight(0.1f)) {
            Badge(containerColor = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatSuccessScreenPreview() {
    ChatSuccessScreen(
        chatState = ChatState(
            currentUser = PreviewParameterProvider.userList[0],
            chatList = PreviewParameterProvider.chatList
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ChatItemPreview() {
    ChatItem(
        chat = PreviewParameterProvider.chatList[0],
        currentUser = User()
    )
}

