package ru.multimodule.character_detail_impl.presentation

import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel

data class CharacterDetailUIState(
    val isLoading: Boolean = false,
    val characterDetail: CharacterDetailModel? = null,
    val errorMsg: String = ""
)