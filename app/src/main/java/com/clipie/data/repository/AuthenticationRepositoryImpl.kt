package com.clipie.data.repository

import com.clipie.domain.entities.State
import com.clipie.domain.model.User
import com.clipie.domain.repository.AuthenticationRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthenticationRepositoryImpl @Inject constructor(
    private val authentication: FirebaseAuth,
    private val database: FirebaseFirestore
) : AuthenticationRepository {
    override fun signUp(email: String, password: String, user: User, result: (State<Unit>) -> Unit) {
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
        document.set(user).addOnSuccessListener { task ->
            result.invoke(State.Success(Unit))
        }.addOnFailureListener {
            result.invoke(State.Error(it.localizedMessage?.toString() ?: "Firebase Error"))
        }
    }
}

