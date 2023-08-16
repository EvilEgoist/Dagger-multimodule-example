package ru.multimodule.utils.common

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

fun ImageView.setImageFromUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}