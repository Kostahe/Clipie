package com.clipie.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User(
    var id: String = "",
    val profileName: String = "",
    val firstName: String = "",
    val secondName: String = "",
    val email: String = "",
    @ServerTimestamp val signUpDate: Date = Date()
)