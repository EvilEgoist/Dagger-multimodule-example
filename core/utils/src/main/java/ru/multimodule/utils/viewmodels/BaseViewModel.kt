package ru.multimodule.utils.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.multimodule.utils.common.Action

abstract class BaseViewModel: ViewModel() {

    protected val _action = MutableSharedFlow<Action>()
    val action = _action.asSharedFlow()
}