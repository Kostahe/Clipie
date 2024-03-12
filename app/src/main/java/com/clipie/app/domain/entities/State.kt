package com.clipie.app.domain.entities

sealed class State<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>: State<T>()
    class Success<T>(data: T?): State<T>(data)
    class Error<T>(message: String): State<T>(message = message)
}