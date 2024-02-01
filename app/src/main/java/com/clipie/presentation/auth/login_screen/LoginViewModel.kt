package com.clipie.presentation.auth.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State as ImmutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.clipie.domain.entities.State
import com.clipie.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel() {

    private val _login: MutableState<State<Unit>> = mutableStateOf(State.Loading())
    val login: ImmutableState<State<Unit>> = _login

    fun login(
        email: String,
        password: String
    ) {
        repository.login(email, password) { state ->
            _login.value = state
        }
    }
}