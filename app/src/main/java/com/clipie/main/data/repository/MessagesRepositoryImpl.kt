package com.clipie.main.data.repository

import com.clipie.app.data.common.FireStoreTable
import com.clipie.app.domain.entities.State
import com.clipie.main.domain.model.Message
import com.clipie.main.domain.repository.MessagesRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
): MessagesRepository {
    override fun sendMessage(message: Message, result: (State<Unit>) -> Unit) {
        fireStore.collection(FireStoreTable.MESSAGE.tableName)
            .add(message)
            .addOnSuccessListener { documentRef ->
                message.id = documentRef.id
                documentRef.set(message)
                result.invoke(State.Success(Unit))
            }
            .addOnFailureListener { exception->
                result.invoke(State.Error(exception.message ?: ""))
            }
    }
}