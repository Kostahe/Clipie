package com.clipie.domain.repository

import com.clipie.domain.entities.State
import com.clipie.domain.model.User

interface AuthenticationRepository {
    fun register(email: String, password: String, user: User, result: (State<Unit>) -> Unit)
    fun updateUser(user: User, result: (State<Unit>) -> Unit)
    fun login(email: String, password: String, result: (State<Unit>) -> Unit)
    fun storeSession(id: String, result: (User?) -> Unit)
    fun getSession(): User?
}