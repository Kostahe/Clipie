package com.clipie.domain.model

data class Chat(
    var id: String = "",
    var message: String = "",
    val sender: String = "",
    val receiver: String = "",
)
