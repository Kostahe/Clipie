package com.clipie.authentication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clipie.util.Resource
import com.clipie.app.domain.models.User
import com.clipie.app.domain.repository.SessionRepository
import com.clipie.authentication.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginState by mutableStateOf<Resource<Unit>>(Resource.Idle)
        private set

    var registerState by mutableStateOf<Resource<Unit>>(Resource.Idle)
        private set

    var forgotPasswordState by mutableStateOf<Resource<Unit>>(Resource.Idle)
        private set

    fun login() {
        viewModelScope.launch {
            loginState = Resource.Loading
            loginState = authenticationRepository.login(email, password)
        }
    }

    fun register() {
        viewModelScope.launch {
            val user = User(email = email, username = username)
            registerState = Resource.Loading
            registerState = authenticationRepository.register(email, password, user)
        }
    }

    fun forgotPassword() {
        viewModelScope.launch {
            forgotPasswordState = Resource.Loading
            forgotPasswordState = authenticationRepository.forgotPassword(email)
        }
    }

    fun getSession() = sessionRepository.getSession()

    fun onEmailChange(email: String) {
        this.email = email
    }

    fun onUsernameChange(username: String) {
        this.username = username
    }

    fun onPasswordChange(password: String) {
        this.password = password
    }

    fun clearData() {
        this.email = ""
        this.username = ""
        this.password = ""
    }
}