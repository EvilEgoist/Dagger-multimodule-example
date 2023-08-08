package ru.multimodule.character_list_impl.presentation.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.multimodule.character_list_impl.domain.model.CharacterModel

abstract class BaseCharactersListItemViewHolder<T : CharacterModel>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    @Suppress("UNCHECKED_CAST")
    fun initBind(item: CharacterModel) {
        (item as? T)?.let {
            bind(it)
        }
    }

    protected abstract fun bind(item: T)
}
