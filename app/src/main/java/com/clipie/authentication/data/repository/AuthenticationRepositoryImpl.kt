package com.clipie.authentication.data.repository

import android.content.SharedPreferences
import com.clipie.util.FireStoreTable
import com.clipie.util.SharedPreferenceName
import com.clipie.util.Resource
import com.clipie.authentication.domain.models.User
import com.clipie.authentication.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import javax.inject.Inject
import androidx.core.content.edit
import kotlinx.coroutines.tasks.await

private const val ERR_FETCH_USER = "Failed to retrieve user data"
private const val ERR_UNKNOWN = "An unknown error occurred"
private const val ERR_DB_UPDATE = "Failed to update database"

class AuthenticationRepositoryImpl @Inject constructor(
    private val authentication: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : AuthenticationRepository {

    override suspend fun register(email: String, password: String, user: User): Resource<Unit> {
        return try {
            val authResult = authentication
                .createUserWithEmailAndPassword(email, password)
                .await()

            val userId = authResult.user?.uid ?: return Resource.Error(ERR_FETCH_USER)
            val createdUser = user.copy(id = userId)

            val updatedResult = updateUser(createdUser)

            if (updatedResult is Resource.Error) {
                return Resource.Error(updatedResult.message ?: ERR_DB_UPDATE)
            }

            saveSession(createdUser)

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: ERR_UNKNOWN)
        }
    }

    override suspend fun updateUser(user: User): Resource<Unit> {
        return try {
            fireStore
                .collection(FireStoreTable.USER.tableName)
                .document(user.id)
                .set(user)
                .await()

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: ERR_DB_UPDATE)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit> {
        return try {
            val authResult = authentication
                .signInWithEmailAndPassword(email, password)
                .await()

            val userId = authResult.user?.uid ?: return Resource.Error(ERR_FETCH_USER)
            val user = fetchUser(userId) ?: return Resource.Error(ERR_FETCH_USER)

            saveSession(user)

            Resource.Success(Unit)

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: ERR_UNKNOWN)
        }
    }

    override suspend fun forgotPassword(email: String): Resource<Unit> {
        return try {
            authentication.sendPasswordResetEmail(email)
                .await()

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: ERR_UNKNOWN)
        }
    }

    private suspend fun fetchUser(userId: String): User? {
        val snapshot = fireStore
            .collection(FireStoreTable.USER.tableName)
            .document(userId)
            .get()
            .await()

        return snapshot.toObject(User::class.java)
    }

    private fun saveSession(user: User) {
        sharedPreferences.edit {
            putString(
                SharedPreferenceName.USER_SESSION.preferenceName,
                gson.toJson(user)
            )
        }
    }

    override fun getSession(): User? {
        val userString = sharedPreferences
            .getString(SharedPreferenceName.USER_SESSION.preferenceName, null)
        return if (userString != null) {
            gson.fromJson(userString, User::class.java)
        } else {
            null
        }
    }
}

