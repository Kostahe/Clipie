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

class AuthenticationRepositoryImpl @Inject constructor(
    private val authentication: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : AuthenticationRepository {

    override fun register(
        email: String,
        password: String,
        user: User,
        result: (Resource<Unit>) -> Unit
    ) {
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                user.id = authResult.user?.uid ?: ""
                updateUser(user, result)
            }.addOnFailureListener { exception ->
                result.invoke(Resource.Error(exception.localizedMessage ?: "Unknown error"))
            }
    }

    override fun updateUser(
        user: User,
        result: (Resource<Unit>) -> Unit
    ) {
        val documentRef = fireStore.collection(FireStoreTable.USER.tableName).document(user.id)
        documentRef.set(user).addOnSuccessListener {
            result.invoke(Resource.Success(Unit))
        }.addOnFailureListener { exception ->
            result.invoke(Resource.Error(exception.localizedMessage ?: "Unknown error"))
        }
    }

    override fun login(
        email: String,
        password: String,
        result: (Resource<Unit>) -> Unit
    ) {
        authentication.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                storeSession(authResult.user?.uid ?: "") { _ ->
                    result.invoke(Resource.Success(Unit))
                }
            }.addOnFailureListener { exception ->
                result.invoke(Resource.Error(exception.localizedMessage ?: "Unknown error"))
            }
    }

    override fun forgotPassword(email: String, result: (Resource<Unit>) -> Unit) {
        authentication.sendPasswordResetEmail(email).addOnSuccessListener {
            result.invoke(Resource.Success(Unit))
        }.addOnFailureListener {
            result.invoke(Resource.Error(it.localizedMessage ?: "Unknown error"))
        }
    }

    override fun storeSession(
        id: String,
        result: (User?) -> Unit
    ) {
        val documentRef = fireStore.collection(FireStoreTable.USER.tableName).document(id)
        documentRef.get().addOnSuccessListener { userResult ->
            val user = userResult.toObject(User::class.java)
            sharedPreferences.edit {
                putString(SharedPreferenceName.USER_SESSION.preferenceName, gson.toJson(user))
            }
            result.invoke(user)
        }.addOnFailureListener {
            result.invoke(null)
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

