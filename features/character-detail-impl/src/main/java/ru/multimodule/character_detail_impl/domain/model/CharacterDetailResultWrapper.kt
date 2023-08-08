package ru.multimodule.character_detail_impl.domain.model

sealed interface CharacterDetailResultWrapper<out T> {
    data class Success<T>(val data: T): CharacterDetailResultWrapper<T>
    object Error
}