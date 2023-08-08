package ru.multimodule.network_api

import ru.multimodule.network_api.model.CharactersResponse
import ru.multimodule.network_api.model.ResponseErrors

fun ServerResponse<CharactersResponse, ResponseErrors>.getErrorMessage(): String{
    return when (this){
        is ServerResponse.Success -> ""
        is ServerResponse.ApiError -> "Серверная ошибка ${this.body.errors}"
        is ServerResponse.NetworkConnectionError -> "Ошибка подключения к сети ${this.error.message}"
        is ServerResponse.UnknownError -> "Неизвестная ошибка, попробуйте позже"
    }
}