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

class AuthenticationRepositoryImpl @Inject constructor(
    private val authentication: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : AuthenticationRepository {

    override fun register(
        email: String,
        password: String,
        user: User, result: (Resource<Unit>) -> Unit
    ) {
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.id = task.result.user?.uid ?: ""
                    updateUser(user) { state ->
                        when (state) {
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                result.invoke(state)
                            }
                            is Resource.Error -> {
                                result.invoke(state)
                            }
                        }
                    }
                } else {
                    result.invoke(Resource.Error("Firebase error"))
                }
            }
    }

    override fun updateUser(
        user: User,
        result: (Resource<Unit>) -> Unit
    ) {
        val documentRef = fireStore.collection(FireStoreTable.USER.tableName).document(user.id)
        documentRef.set(user).addOnSuccessListener {
            result.invoke(Resource.Success(Unit))
        }.addOnFailureListener {
            result.invoke(Resource.Error(it.localizedMessage?.toString() ?: "Firebase Error"))
        }
    }

    override fun login(
        email: String,
        password: String,
        result: (Resource<Unit>) -> Unit
    ) {
        authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                storeSession(task.result.user?.uid ?: "") { user ->
                    if (user == null) {
                        result.invoke(Resource.Error("Authentication succeed, but session didn't save user"))
                    } else {
                        result.invoke(Resource.Success(Unit))
                    }
                }
            } else {
                result.invoke(Resource.Error(task.exception.toString()))
            }
        }
    }

    override fun forgotPassword(email: String, result: (Resource<Unit>) -> Unit) {
        authentication.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                result.invoke(Resource.Success(Unit))
            } else {
                result.invoke(Resource.Error(task.exception?.message.toString()))
            }
        }.addOnFailureListener {
            result.invoke(Resource.Error(it.localizedMessage?.toString() ?: ""))
        }
    }

    override fun storeSession(
        id: String,
        result: (User?) -> Unit
    ) {
        val documentRef = fireStore.collection(FireStoreTable.USER.tableName).document(id)
        documentRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result?.toObject(User::class.java)
                sharedPreferences.edit()
                    .putString(SharedPreferenceName.USER_SESSION.preferenceName, gson.toJson(user))
                    .apply()
                result.invoke(user)
            } else {
                result.invoke(null)
            }
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

