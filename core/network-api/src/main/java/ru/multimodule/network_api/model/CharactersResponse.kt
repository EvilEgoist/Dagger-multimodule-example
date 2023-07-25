package ru.multimodule.network_api.model

@kotlinx.serialization.Serializable
data class CharactersResponse(
    val results: List<CharacterDto>
)
