package com.clipie.authentication.domain.models

import java.util.Date

data class User(
    var id: String = "",
    val username: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val totalPosts: String = "0",
    val bio: String = "",
    val following: List<String> = listOf(),
    val followers: List<String> = listOf(),
    val signUpDate: Date = Date(),
)