package ru.multimodule.character_list_impl.presentation.view_models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CharactersViewModel: ViewModel() {

    private val _charactersFlow = MutableStateFlow<>()
}