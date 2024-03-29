package com.clipie.main.domain.repository

import com.clipie.app.domain.entities.State
import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat
import com.clipie.main.domain.model.Message

interface MessagesRepository {
    fun getChatListFromCurrentUser(result: (State<List<Chat>>) -> Unit)
    fun getMessagesFromChat(chat: Chat, result: (State<List<Message>>) -> Unit)
    fun sendMessage(message: Message, result: (State<Unit>) -> Unit)
    fun getUsersFromChat(chat: Chat, result: (State<List<User>>) -> Unit)
}