package com.clipie.authentication.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State as ImmutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.clipie.util.Resource
import com.clipie.authentication.domain.models.User
import com.clipie.authentication.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _login: MutableState<Resource<Unit>> = mutableStateOf(Resource.Loading())
    val login: ImmutableState<Resource<Unit>> = _login

    private val _register: MutableState<Resource<Unit>> = mutableStateOf(Resource.Loading())
    val register: ImmutableState<Resource<Unit>> = _register

    private val _forgotPassword: MutableState<Resource<Unit>> = mutableStateOf(Resource.Loading())
    val forgotPassword: ImmutableState<Resource<Unit>> = _forgotPassword

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

    fun getSession() = repository.getSession()

}