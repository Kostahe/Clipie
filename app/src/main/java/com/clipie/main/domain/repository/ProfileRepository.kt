package com.clipie.main.domain.repository

import android.net.Uri

interface ProfileRepository {
    fun uploadAvatar(userId: String, imageUri: Uri, result: (Result<String>) -> Unit)
}