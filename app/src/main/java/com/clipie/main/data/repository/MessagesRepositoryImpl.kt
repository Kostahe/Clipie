package com.clipie.main.data.repository

import com.clipie.util.FireStoreTable
import com.clipie.util.Resource
import com.clipie.main.domain.model.Message
import com.clipie.main.domain.repository.MessagesRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
): MessagesRepository {
    override fun sendMessage(message: Message, result: (Resource<Unit>) -> Unit) {
        fireStore.collection(FireStoreTable.MESSAGE.tableName)
            .add(message)
            .addOnSuccessListener { documentRef ->
                message.id = documentRef.id
                documentRef.set(message)
                result.invoke(Resource.Success(Unit))
            }
            .addOnFailureListener { exception->
                result.invoke(Resource.Error(exception.message ?: ""))
            }
    }
}