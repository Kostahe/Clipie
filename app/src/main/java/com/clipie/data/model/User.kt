package com.clipie.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User(
    var id: String = "",
    val profileName: String = "",
    val firstName: String = "",
    val secondName: String = "",
    @ServerTimestamp val signUpDate: Date = Date()
)
