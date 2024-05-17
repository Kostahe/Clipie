package com.clipie.main.presentation.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.clipie.main.domain.model.Chat
import com.clipie.main.domain.model.Message as Message

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    listMessage: List<Message>
) {
    LazyColumn(modifier = modifier) {
        items(listMessage) { message ->
            MessageItem(message = message)
        }
    }
}

@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    message: Message,
) {
    val sender = message.sender
    Row {
        AsyncImage(model = , contentDescription = )
    }
}