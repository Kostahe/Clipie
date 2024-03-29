package com.clipie.main.data.repository

import com.clipie.app.data.common.FireStoreTable
import com.clipie.app.domain.entities.State
import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat
import com.clipie.main.domain.model.Message
import com.clipie.main.domain.repository.MessagesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val authentication: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : MessagesRepository {


    override fun getChatListFromCurrentUser(result: (State<List<Chat>>) -> Unit) {
        fireStore.collection(FireStoreTable.CHAT.tableName)
            .whereArrayContains("usersId", authentication.currentUser?.uid.orEmpty())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val chatList: List<Chat> = task.result.toObjects(Chat::class.java)
                    result.invoke(State.Success(chatList))
                } else {
                    result.invoke(State.Error(task.exception?.message.orEmpty()))
                }
            }
    }

    override fun getMessagesFromChat(chat: Chat, result: (State<List<Message>>) -> Unit) {
        fireStore.collection(FireStoreTable.MESSAGE.tableName)
            .whereEqualTo("chatId", chat.id)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val messageList: List<Message> = task.result.toObjects(Message::class.java)
                    result.invoke(State.Success(messageList))
                } else {
                    result.invoke(State.Error(task.exception?.message.orEmpty()))
                }
            }
    }

    override fun sendMessage(message: Message, result: (State<Unit>) -> Unit) {
        fireStore.collection(FireStoreTable.MESSAGE.tableName)
            .add(message)
            .addOnSuccessListener { documentRef ->
                message.id = documentRef.id
                documentRef.set(message)
                result.invoke(State.Success(Unit))
            }
            .addOnFailureListener { exception ->
                result.invoke(State.Error(exception.message ?: ""))
            }
    }

    override fun getUsersFromChat(chat: Chat, result: (State<List<User>>) -> Unit) {
        fireStore.collection(FireStoreTable.USER.tableName)
            .whereIn("id", chat.usersId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userList = task.result?.toObjects(User::class.java)
                    result.invoke(State.Success(userList))
                } else {
                    result.invoke(State.Error(task.exception?.message.orEmpty()))
                }

            }
    }
}