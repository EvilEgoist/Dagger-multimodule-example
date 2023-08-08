package ru.multimodule.character_list_impl.presentation.view_models

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.multimodule.character_list_impl.domain.model.CharactersCallResultWrapper
import ru.multimodule.character_list_impl.domain.usecase.GetCharactersUseCase
import ru.multimodule.character_list_impl.presentation.uistate.CharactersListUIState
import ru.multimodule.utils.common.Action
import ru.multimodule.utils.viewmodels.BaseViewModel

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    private val _uiStateFlow: MutableStateFlow<CharactersListUIState> =
        MutableStateFlow(CharactersListUIState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.Default) {
            _uiStateFlow.value = CharactersListUIState(isLoading = true)
            val responseState = getCharactersUseCase.getCharactersList()
            when (responseState) {
                is CharactersCallResultWrapper.Success -> {
                    _uiStateFlow.value =
                        CharactersListUIState(charactersList = responseState.characterList)
                }
                is CharactersCallResultWrapper.Error -> {
                    _uiStateFlow.value = CharactersListUIState(
                        charactersList = responseState.charactersList.orEmpty(),
                        errorMsg = responseState.message
                    )
                    _action.emit(Action.ShowToast(responseState.message))
                }
            }
        }
    }

    fun errorMsgHandled(){
        _uiStateFlow.value = _uiStateFlow.value.copy(errorMsg = "")
    }
}