package com.clipie.presentation.auth.registration_screen

import com.clipie.domain.entities.State

data class RegistrationState(
    var state: State<Unit> = State.Loading(),
)
