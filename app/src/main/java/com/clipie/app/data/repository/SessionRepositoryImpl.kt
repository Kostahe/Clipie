package com.clipie.app.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.clipie.app.domain.models.User
import com.clipie.app.domain.repository.SessionRepository
import com.clipie.util.SharedPreferenceName
import com.google.gson.Gson
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : SessionRepository {

    override fun saveSession(user: User) {
        sharedPreferences.edit {
            putString(
                SharedPreferenceName.USER_SESSION.preferenceName,
                gson.toJson(user)
            )
        }
    }

    override fun getSession(): User? = sharedPreferences
        .getString(SharedPreferenceName.USER_SESSION.preferenceName, null)
        ?.let { userString ->
            gson.fromJson(userString, User::class.java)
        }

    override fun clearSession() {
        sharedPreferences.edit {
            remove(SharedPreferenceName.USER_SESSION.preferenceName)
        }
    }
}