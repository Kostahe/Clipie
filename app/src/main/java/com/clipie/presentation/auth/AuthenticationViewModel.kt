package com.clipie.presentation.auth

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
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _login: MutableState<State<Unit>> = mutableStateOf(State.Loading())
    val login: ImmutableState<State<Unit>> = _login

    private val _register: MutableState<State<Unit>> = mutableStateOf(State.Loading())
    val register: ImmutableState<State<Unit>> = _register

    private val _forgotPassword: MutableState<State<Unit>> = mutableStateOf(State.Loading())
    val forgotPassword: ImmutableState<State<Unit>> = _forgotPassword

    fun login(
        email: String,
        password: String
    ) {
        repository.login(email, password) { state ->
            _login.value = state
        }
    }

    fun register(
        email: String,
        password: String,
        user: User
    ) {
        repository.register(email, password, user) { state ->
            _register.value = state
        }
    }

    fun forgotPassword(
        email: String
    ) {
        repository.forgotPassword(email) { state ->
            _forgotPassword.value = state
        }
    }
}