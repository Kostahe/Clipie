package com.clipie.presentation.messages

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State as ImmutableState
import androidx.lifecycle.ViewModel
import com.clipie.domain.entities.State
import com.clipie.domain.model.Message
import com.clipie.domain.repository.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository
): ViewModel() {

    private var _sendMessage: MutableState<State<Unit>> = mutableStateOf(State.Loading())
    val sendMessage: ImmutableState<State<Unit>> = _sendMessage
    fun sendMessages(message: Message) {
        messagesRepository.sendMessage(message) { state ->
            _sendMessage.value = state
        }
    }
}