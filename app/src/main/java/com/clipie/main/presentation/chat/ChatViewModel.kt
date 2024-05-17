@file:Suppress("NAME_SHADOWING")

package com.clipie.main.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clipie.authentication.domain.models.User
import com.clipie.authentication.domain.repository.AuthenticationRepository
import com.clipie.main.domain.model.Chat
import com.clipie.main.domain.repository.ChatRepository
import com.clipie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private var _state: MutableStateFlow<Resource<ChatState>> = MutableStateFlow(Resource.Loading())
    val state = _state.asStateFlow()

    private var _userList: MutableStateFlow<Resource<List<User>>> =
        MutableStateFlow(Resource.Loading())
    val userList = _userList.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val chatList = chatRepository.getChatListFromCurrentUser()
                val currentUser = authenticationRepository.getSession()
                chatList?.let { chatList ->
                    currentUser?.let { user ->
                        _state.value = Resource.Success(
                            ChatState(
                                chatList = chatList,
                                currentUser = user
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                _state.value = Resource.Error(e.localizedMessage ?: "")
            }
        }
    }

    fun onSearchChangeText(searchText: String) {
        viewModelScope.launch {
            _userList.value = Resource.Loading()
            val currentUser = authenticationRepository.getSession()
            delay(1000)
            val userList = chatRepository.getUserListFromSearchedText(searchText = searchText)
            userList?.let { userList ->
                currentUser?.let { currentUser ->
                    userList.toMutableList().remove(currentUser)
                    _userList.value = Resource.Success(userList)
                }
            }
        }
    }
}

data class ChatState(
    val chatList: List<Chat> = listOf(),
    val currentUser: User
)

