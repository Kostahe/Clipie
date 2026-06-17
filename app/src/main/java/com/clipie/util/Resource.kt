package com.clipie.util

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null
) {
    object Idle : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    class Success<T>(data: T? = null) : Resource<T>(data = data)
    class Error(message: String) : Resource<Nothing>(message = message)
}