package com.clipie.main.domain.model

data class Message(
    var id: String = "",
    val chatId: String = "",
    val message: String = "",
    val sender: String = "",
    val messageStatus: MessageStatus = MessageStatus.NO_SENT,
    val messageType: MessageType = MessageType.TEXT
)

enum class MessageStatus{
    NO_SENT,
    NO_READ,
    READ
}

enum class MessageType{
    TEXT,
    PHOTO
}
