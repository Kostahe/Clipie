package com.clipie.main.domain.repository

import com.clipie.util.Resource
import com.clipie.main.domain.model.Message

interface MessagesRepository {
    fun sendMessage(message: Message, result: (Resource<Unit>) -> Unit)
}