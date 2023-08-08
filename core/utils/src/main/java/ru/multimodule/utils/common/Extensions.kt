package ru.multimodule.utils.common

import android.view.View
import kotlinx.coroutines.*

fun View.setDebounceClickListener(
    delayInMillis: Long = 500,
    coroutineScope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate
    ),
    onClick: () -> Unit
) {
    var job: Job? = null
    setOnClickListener {
        job?.cancel()
        job = coroutineScope.launch {
            onClick.invoke()
            delay(delayInMillis)
        }
    }
}