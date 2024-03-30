package com.clipie.main.domain.repository

import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat
import com.clipie.main.domain.model.Message
import com.clipie.util.Resource

interface MessagesRepository {
    fun getChatListFromCurrentUser(result: (Resource<List<Chat>>) -> Unit)
    fun getMessagesFromChat(chat: Chat, result: (Resource<List<Message>>) -> Unit)
    fun getUsersFromChat(chat: Chat, result: (Resource<List<User>>) -> Unit)
}