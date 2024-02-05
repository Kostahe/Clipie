package com.clipie.domain.model

import java.util.Date

data class User(
    var id: String = "",
    val username: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val totalPosts: String = "",
    val bio: String = "",
    val following: List<String>,
    val followers: List<String>,
    val signUpDate: Date = Date(),
)