package com.clipie.main.domain.repository

import com.clipie.main.domain.model.Chat
import com.clipie.main.domain.model.Message

interface ChatRepository {
    suspend fun getChatListFromCurrentUser(): List<Chat>?
    suspend fun getMessagesFromChat(chat: Chat): List<Message>?
}