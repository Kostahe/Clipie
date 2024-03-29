package com.clipie.main.domain.model

data class Chat(
    var id: String = "",
    val name: String = "",
    val usersId: List<String> = listOf(),
    val lastMessageId: String = ""
)