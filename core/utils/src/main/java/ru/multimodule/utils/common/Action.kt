package ru.multimodule.utils.common

import androidx.annotation.StringRes

sealed class Action {
    class ShowToast(val message: String): Action()
}