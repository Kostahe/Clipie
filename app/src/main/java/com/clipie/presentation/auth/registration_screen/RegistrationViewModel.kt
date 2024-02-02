package com.clipie.presentation.auth.registration_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State as ImmutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.clipie.domain.entities.State
import com.clipie.domain.model.User
import com.clipie.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel() {

    private val _createAccount: MutableState<State<Unit>> = mutableStateOf(State.Loading())
    val createAccount: ImmutableState<State<Unit>> = _createAccount

    fun createUser(email: String, password: String, user: User) {
        repository.register(email, password, user) { state ->
            _createAccount.value = state
        }
    }
}