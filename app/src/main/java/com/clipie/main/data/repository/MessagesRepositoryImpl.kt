package com.clipie.main.data.repository


import com.clipie.authentication.domain.models.User
import com.clipie.main.domain.model.Chat
import com.clipie.util.FireStoreTable
import com.clipie.util.Resource
import com.clipie.main.domain.model.Message
import com.clipie.main.domain.repository.MessagesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val fireStore: FirebaseFirestore
) : MessagesRepository {

    override fun getChatListFromCurrentUser(result: (Resource<List<Chat>>) -> Unit) {
        fireStore.collection(FireStoreTable.CHAT.tableName)
            .whereArrayContains("usersId", firebaseAuth.currentUser?.uid.orEmpty()).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val chatList: List<Chat> = task.result.toObjects(Chat::class.java)
                    result.invoke(Resource.Success(chatList))
                } else {
                    result.invoke(Resource.Error(task.exception?.message.orEmpty()))
                }
            }
    }

    override fun getMessagesFromChat(chat: Chat, result: (Resource<List<Message>>) -> Unit) {
        fireStore.collection(FireStoreTable.MESSAGE.tableName).whereEqualTo("chatId", chat.id).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val messageList: List<Message> = task.result.toObjects(Message::class.java)
                    result.invoke(Resource.Success(messageList))
                } else {
                    result.invoke(Resource.Error(task.exception?.message.orEmpty()))
                }
            }
    }


    override fun getUsersFromChat(chat: Chat, result: (Resource<List<User>>) -> Unit) {
        fireStore.collection(FireStoreTable.USER.tableName).whereIn("id", chat.usersId).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userList = task.result?.toObjects(User::class.java)
                    result.invoke(Resource.Success(userList))
                } else {
                    result.invoke(Resource.Error(task.exception?.message.orEmpty()))
                }
            }
    }
}