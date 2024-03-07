package com.clipie.data.repository

import com.clipie.data.common.FireStoreTable
import com.clipie.domain.entities.State
import com.clipie.domain.model.Message
import com.clipie.domain.repository.MessagesRepository
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