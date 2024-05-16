package com.clipie.main.data.repository

import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat
import com.clipie.util.FireStoreTable
import com.clipie.main.domain.model.Message
import com.clipie.main.domain.repository.ChatRepository
import com.clipie.util.FireStoreField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class ChatRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : ChatRepository {

    override suspend fun getChatListFromCurrentUser(): List<Chat>? {
        return try {
            fireStore.collection(FireStoreTable.CHAT.tableName)
                .whereArrayContains(FireStoreField.USERSID.fieldName, firebaseAuth.currentUser?.uid.orEmpty())
                .get()
                .await()
                .toObjects(Chat::class.java)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
    }

    override suspend fun getMessagesFromChat(chat: Chat): List<Message>? {
        return try {
            fireStore.collection(FireStoreTable.MESSAGE.tableName)
                .whereEqualTo(FireStoreField.CHAT_ID.fieldName, chat.id)
                .get()
                .await()
                .toObjects(Message::class.java)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            null
        }
    }

    override suspend fun getUserListFromSearchedText(searchText: String): List<User>? {
        return try {
            fireStore.collection(FireStoreTable.USER.tableName)
                .whereIn(FireStoreField.USERNAME.fieldName, searchText.split(" "))
                .get()
                .await()
                .toObjects(User::class.java)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
    }


//    override suspend fun getUsersFromChat(chat: Chat): List<User>? {
//        return try {
//            fireStore.collection(FireStoreTable.USER.tableName)
//                .whereIn("id", chat.usersId)
//                .get()
//                .await()
//                .toObjects(User::class.java)
//        } catch (e: Exception) {
//            if (e is CancellationException) throw e
//            null
//        }
//    }
}