package ru.multimodule.character_list_impl.presentation.uistate

import ru.multimodule.character_list_impl.domain.model.CharacterModel

data class CharactersListUIState(
    val isLoading: Boolean = false,
    val charactersList: List<CharacterModel> = emptyList(),
    val errorMsg: String = ""
)