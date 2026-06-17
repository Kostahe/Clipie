package com.clipie.main.data.repository

import android.net.Uri
import com.clipie.main.domain.repository.ProfileRepository
import com.clipie.util.FireStoreTable
import com.clipie.util.FireStoreUserField
import com.clipie.util.Resource
import com.clipie.util.StoragePath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val ERR_UNKNOWN = "An unknown error occurred"

class ProfileRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
) : ProfileRepository {

    override suspend fun uploadAvatar(userId: String, imageUri: Uri): Resource<String> {
        return try {
            val reference = storage.reference.child("${StoragePath.AVATARS.path}/$userId")
            reference
                .putFile(imageUri)
                .await()
            val downloadUrl = reference.downloadUrl.await().toString()

            Resource.Success(downloadUrl)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: ERR_UNKNOWN)
        }
    }

    override suspend fun updateProfileDetails(
        userId: String,
        username: String,
        bio: String,
        imageUrl: String
    ): Resource<Unit> {
        return try {
            val updates = mapOf(
                FireStoreUserField.USERNAME.fieldName to username,
                FireStoreUserField.BIO.fieldName to bio,
                FireStoreUserField.IMAGE_URL.fieldName to imageUrl
            )

            firestore
                .collection(FireStoreTable.USER.tableName)
                .document(userId)
                .update(updates)
                .await()

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: ERR_UNKNOWN)
        }
    }
}