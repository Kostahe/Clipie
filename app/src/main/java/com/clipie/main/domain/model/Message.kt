package com.clipie.main.domain.model

data class Message(
    var id: String = "",
    var message: String = "",
    val sender: String = "",
    val receiver: String = "",
)
