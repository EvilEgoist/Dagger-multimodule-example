package ru.multimodule.character_list_impl.domain.model

sealed class CharactersCallResultWrapper <T> {
    data class Success<T>(val characterList: T) : CharactersCallResultWrapper<T>()
    data class Error<T>(val charactersList: T? = null, val message: String = "") :
        CharactersCallResultWrapper<T>()
}