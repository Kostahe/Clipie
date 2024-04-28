package com.clipie.util

import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat


object PreviewParameterProvider {
    val userList = listOf<User>(
        User(
            id = "1",
            username = "john_doe",
            email = "john@example.com"
        ),
        User(
            id = "2",
            username = "jane_doe",
            email = "jane@example.com"
        ),
        User(
            id = "3",
            username = "alex_smith",
            email = "alex@example.com"
        ),
        User(
            id = "4",
            username = "emily_white",
            email = "emily@example.com"
        )
    )
    val chatList = listOf<Chat>(
        Chat(
            id = "1",
            name = "Friends Chat",
            users = listOf(userList[0], userList[1], userList[2]),
            lastMessageId = "msg1"
        ),
        Chat(
            id = "2",
            name = "Family Chat",
            users = listOf(userList[0], userList[3]),
            lastMessageId = "msg2"
        ),
        Chat(
            id = "3",
            name = "Work Chat",
            users = listOf(userList[0], userList[2]),
            lastMessageId = "msg3"
        )
    )
}