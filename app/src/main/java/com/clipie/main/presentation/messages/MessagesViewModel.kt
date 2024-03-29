package com.clipie.main.presentation.messages

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State as ImmutableState
import androidx.lifecycle.ViewModel
import com.clipie.app.domain.entities.State
import com.clipie.main.domain.model.Message
import com.clipie.main.domain.repository.MessagesRepository
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