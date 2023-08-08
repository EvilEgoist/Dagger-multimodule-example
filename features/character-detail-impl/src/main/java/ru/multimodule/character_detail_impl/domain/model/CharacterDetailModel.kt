package ru.multimodule.character_detail_impl.domain.model

data class CharacterDetailModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)