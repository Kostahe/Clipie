package com.clipie.main.data.repository

import android.net.Uri
import com.clipie.main.domain.repository.ProfileRepository
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage
) : ProfileRepository {
    override fun uploadAvatar(
        userId: String,
        imageUri: Uri,
        result: (Result<String>) -> Unit
    ) {
        val reference = storage.reference.child("avatars/$userId")

        reference.putFile(imageUri)
            .addOnSuccessListener {
                reference.downloadUrl.addOnSuccessListener { uri ->
                    result.invoke(Result.success(uri.toString()))
                }.addOnFailureListener { exception ->
                    result.invoke(Result.failure(exception))
                }
            }.addOnFailureListener { exception ->
                result.invoke(Result.failure(exception))
            }
    }

}