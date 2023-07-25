package ru.multimodule.network_api

import java.io.IOException

sealed class ServerResponse<out T : Any, out U : Any> {
    data class Success<T : Any>(val body: T) : ServerResponse<T, Nothing>()
    data class ApiError<U : Any>(val body: U, val code: Int) : ServerResponse<Nothing, U>()
    data class NetworkConnectionError(val error: IOException) : ServerResponse<Nothing, Nothing>()
    data class UnknownError(val error: Throwable?) : ServerResponse<Nothing, Nothing>()
}