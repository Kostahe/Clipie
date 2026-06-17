package com.clipie.main.domain.repository

import android.net.Uri
import com.clipie.util.Resource

interface ProfileRepository {
    suspend fun uploadAvatar(userId: String, imageUri: Uri): Resource<String>
    suspend fun updateProfileDetails(
        userId: String,
        username: String,
        bio: String,
        imageUrl: String
    ) : Resource<Unit>
}