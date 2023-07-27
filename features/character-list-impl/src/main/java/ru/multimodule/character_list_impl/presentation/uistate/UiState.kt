package ru.multimodule.character_list_impl.presentation.uistate

import ru.multimodule.character_list_impl.domain.model.CharacterModel

sealed class CharactersListUiState {
    object Empty : CharactersListUiState()
    object Loading : CharactersListUiState()
    class Loaded(val data: CharacterModel) : CharactersListUiState()
    class Error(val message: String) : CharactersListUiState()
}
