package com.clipie.main.domain.model

import com.clipie.authentication.domain.models.User

data class Chat(
    var id: String = "",
    val name: String = "",
    val userList: List<User> = listOf(),
    val lastMessageId: String = ""
)