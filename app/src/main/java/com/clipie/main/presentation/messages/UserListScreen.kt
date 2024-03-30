package com.clipie.main.presentation.messages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipie.app.domain.entities.State
import com.clipie.main.domain.model.Chat
import com.clipie.main.presentation.MainViewModel


@Composable
fun UserListScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<MainViewModel>()
    LazyColumn {
        viewModel.getChatList { chatState ->

            when(chatState) {
                is State.Loading -> {

                }
                is State.Success -> {
                    chatState.data?.let { chatList ->
                        items(chatList) { chat ->
                            ChatRow(
                                chat = chat,

                            )
                        }
                    }
                }
                is State.Error -> {

                }
            }
        }
    }
}

@Composable
fun ChatRow(
    modifier: Modifier = Modifier,
    chat: Chat,
) {
    val viewModel = hiltViewModel<MainViewModel>()
    viewModel.getUsersFromChat(chat) {
        when(it) {
            is State.Loading -> {
                
            }
            is State.Success -> {
                Text(text = "a")
            }
            is State.Error -> {
                
            }
        }
    }
    
}