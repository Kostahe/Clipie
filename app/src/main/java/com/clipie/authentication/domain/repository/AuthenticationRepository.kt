package com.clipie.authentication.domain.repository

import com.clipie.util.Resource
import com.clipie.app.domain.models.User

interface AuthenticationRepository {
    suspend fun register(email: String, password: String, user: User): Resource<Unit>
    suspend fun updateUser(user: User): Resource<Unit>
    suspend fun forgotPassword(email: String): Resource<Unit>
    suspend fun login(email: String, password: String): Resource<Unit>
}