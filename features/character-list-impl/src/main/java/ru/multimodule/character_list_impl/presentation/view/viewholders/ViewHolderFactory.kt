package ru.multimodule.character_list_impl.presentation.view.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.multimodule.character_list_impl.R
import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.presentation.view.adapters.CharactersRVAdapter

class ViewHolderFactory {

    fun createViewHolder(
        parentView: ViewGroup,
        productListener: CharactersRVAdapter.Listener,
    ): BaseCharactersListItemViewHolder<out CharacterModel> {
        return CharactersListItemViewHolder(
            LayoutInflater.from(parentView.context)
                .inflate(R.layout.character_list_item, parentView, false),
            productListener,
        )
    }
}