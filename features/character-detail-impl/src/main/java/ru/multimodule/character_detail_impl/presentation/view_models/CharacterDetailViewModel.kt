package ru.multimodule.character_detail_impl.presentation.view_models

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.multimodule.character_detail_impl.domain.usecase.GetCharacterDetailUseCase
import ru.multimodule.character_detail_impl.presentation.CharacterDetailUIState
import ru.multimodule.utils.viewmodels.BaseViewModel

class CharacterDetailViewModel(
    private val useCase: GetCharacterDetailUseCase
) : BaseViewModel() {

    private val _characterDetailUiStateFlow: MutableStateFlow<CharacterDetailUIState> =
        MutableStateFlow(CharacterDetailUIState())
    val characterDetailUiStateFlow = _characterDetailUiStateFlow.asStateFlow()

    fun getCharacter(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _characterDetailUiStateFlow.value = CharacterDetailUIState(isLoading = true)
                _characterDetailUiStateFlow.value = CharacterDetailUIState(
                    isLoading = false,
                    useCase.getCharacterDetail(characterId)
                )
            } catch (e: Exception) {
                _characterDetailUiStateFlow.value =
                    CharacterDetailUIState(errorMsg = "Loading error ${e.message}")
            }
        }
    }

}