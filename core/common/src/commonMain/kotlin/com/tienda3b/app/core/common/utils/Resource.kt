package com.tienda3b.app.core.common.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val message: String, val cause: Throwable? = null) : Resource<Nothing>
    data object Loading : Resource<Nothing>
}

fun <T> Resource<T>.getOrNull(): T? = (this as? Resource.Success)?.data


inline fun <T> flowResource(
    crossinline block: suspend () -> T
): Flow<Resource<T>> = flow {
    emit(Resource.Loading)
    val data = block()
    emit(Resource.Success(data))
}.catch { e ->
    emit(Resource.Error(message = mapThrowableToMessage(e), cause = e))
}

fun mapThrowableToMessage(t: Throwable): String {
    val msg = when (t) {
        is java.net.SocketTimeoutException -> "Tiempo de espera agotado."
        else -> t.message ?: "Error desconocido."
    }
    return msg
}