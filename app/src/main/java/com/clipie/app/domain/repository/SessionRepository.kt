package com.clipie.app.domain.repository

import com.clipie.app.domain.models.User

interface SessionRepository {
    fun saveSession(user: User)
    fun getSession(): User?
    fun clearSession()
}