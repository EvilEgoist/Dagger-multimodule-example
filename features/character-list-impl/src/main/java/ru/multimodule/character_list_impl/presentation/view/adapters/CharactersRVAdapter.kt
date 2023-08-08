package ru.multimodule.character_list_impl.presentation.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.presentation.view.viewholders.BaseCharactersListItemViewHolder
import ru.multimodule.character_list_impl.presentation.view.viewholders.CharactersListItemViewHolder
import ru.multimodule.character_list_impl.presentation.view.viewholders.ViewHolderFactory

class CharactersRVAdapter(
    private val listener: Listener,
    private val viewHolderFactory: ViewHolderFactory
) : ListAdapter<CharacterModel, BaseCharactersListItemViewHolder<out CharacterModel>>(
    BaseCharactersDiffUtil()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseCharactersListItemViewHolder<out CharacterModel> {
        return viewHolderFactory.createViewHolder(parent, listener)
    }

    override fun onBindViewHolder(
        holder: BaseCharactersListItemViewHolder<out CharacterModel>,
        position: Int
    ) {
        holder.initBind(getItem(position))
    }

    interface Listener {
        fun onClickCharacter(holder: CharactersListItemViewHolder, characterId: Int)
    }

    private class BaseCharactersDiffUtil : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(
            oldItem: CharacterModel,
            newItem: CharacterModel
        ): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: CharacterModel,
            newItem: CharacterModel
        ): Boolean = oldItem == newItem
    }
}