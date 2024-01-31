package com.clipie.data.repository

import com.clipie.domain.entities.State
import com.clipie.domain.model.User
import com.clipie.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authentication: FirebaseAuth,
    private val database: FirebaseFirestore
) : AuthenticationRepository {
    override fun createUser(email: String, password: String, user: User, result: (State<Unit>) -> Unit) {
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.id = task.result.user?.uid ?: ""
                    updateUser(user) { state ->
                        when (state) {
                            is State.Loading -> {

                            }
                            is State.Success -> {
                                result.invoke(state)
                            }
                            is State.Error -> {
                                result.invoke(state)
                            }
                        }
                    }
                } else {
                    result.invoke(State.Error("Firebase error"))
                }
            }
    }

    override fun updateUser(user: User, result: (State<Unit>) -> Unit)  {
        val document = database.collection("user").document(user.id)
        document.set(user).addOnSuccessListener {
            result.invoke(State.Success(Unit))
        }.addOnFailureListener {
            result.invoke(State.Error(it.localizedMessage?.toString() ?: "Firebase Error"))
        }
    }
}

