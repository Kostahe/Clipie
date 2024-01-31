package com.clipie.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User(
    var id: String = "",
    val username: String = "",
    val email: String = "",
    @ServerTimestamp val signUpDate: Date = Date()
)
