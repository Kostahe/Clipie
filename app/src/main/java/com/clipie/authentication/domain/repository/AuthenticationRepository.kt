package com.clipie.authentication.domain.repository

import com.clipie.app.domain.entities.State
import com.clipie.authentication.domain.models.User

interface AuthenticationRepository {
    fun register(email: String, password: String, user: User, result: (State<Unit>) -> Unit)
    fun updateUser(user: User, result: (State<Unit>) -> Unit)
    fun forgotPassword(email: String, result: (State<Unit>) -> Unit)
    fun login(email: String, password: String, result: (State<Unit>) -> Unit)
    fun storeSession(id: String, result: (User?) -> Unit)
    fun getSession(): User?
}