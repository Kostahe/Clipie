package com.clipie.main.domain.repository

import com.clipie.app.domain.entities.State
import com.clipie.main.domain.model.Message

interface MessagesRepository {
    fun sendMessage(message: Message, result: (State<Unit>) -> Unit)
}