package com.clipie.util

enum class FireStoreTable(val tableName: String) {
    USER("user"),
    MESSAGE("message"),
    CHAT("chat")
}

enum class FireStoreField(val fieldName: String) {
    USERNAME("username"),
    CHAT_ID("chatId"),
    USERSID("usersId")
}