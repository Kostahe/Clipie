package com.clipie.authentication.domain.repository

import com.clipie.util.Resource
import com.clipie.authentication.domain.models.User

interface AuthenticationRepository {
    fun register(email: String, password: String, user: User, result: (Resource<Unit>) -> Unit)
    fun updateUser(user: User, result: (Resource<Unit>) -> Unit)
    fun forgotPassword(email: String, result: (Resource<Unit>) -> Unit)
    fun login(email: String, password: String, result: (Resource<Unit>) -> Unit)
    fun storeSession(id: String, result: (User?) -> Unit)
    fun getSession(): User?
}