package com.clipie.presentation.auth.registration_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.clipie.domain.model.User
import com.clipie.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel() {

    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    fun createUser(email: String, password: String, user: User) {
        repository.createUser(email, password, user) {
            _state.value.state = it
        }
    }
}