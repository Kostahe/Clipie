package com.clipie.domain.repository

import com.clipie.domain.entities.State
import com.clipie.domain.model.User

interface AuthenticationRepository {
    fun createUser(email: String, password: String, user: User, result: (State<Unit>) -> Unit)
    fun updateUser(user: User, result: (State<Unit>) -> Unit)
}