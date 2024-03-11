package com.clipie.domain.repository

import com.clipie.domain.entities.State
import com.clipie.domain.model.Message

interface MessagesRepository {
    fun sendMessage(message: Message, result: (State<Unit>) -> Unit)
}