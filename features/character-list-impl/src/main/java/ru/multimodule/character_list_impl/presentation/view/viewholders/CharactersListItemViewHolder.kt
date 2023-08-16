package ru.multimodule.character_list_impl.presentation.view.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.card.MaterialCardView
import ru.multimodule.character_list_impl.R
import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.presentation.view.adapters.CharactersRVAdapter
import ru.multimodule.utils.common.setDebounceClickListener
import ru.multimodule.utils.common.setImageFromUrl

class CharactersListItemViewHolder(
    private val itemView: View,
    private val listener: CharactersRVAdapter.Listener
): BaseCharactersListItemViewHolder<CharacterModel>(itemView) {

    private var characterNameTV: TextView? = null
    private var characterImageIV: ImageView? = null
    private var characterInList: CharacterModel? = null
    private var characterCardView: MaterialCardView? = null

    init {
        itemView.apply {
            characterNameTV = findViewById(R.id.characterNameTV)
            characterImageIV = findViewById(R.id.characterImageIV)
            characterCardView = findViewById(R.id.characterCardView)

            initListener()
        }
    }

    private fun initListener(){
        characterCardView?.setDebounceClickListener {
            characterInList?.let { listener.onClickCharacter(this, it.id) }
        }
    }

    override fun bind(item: CharacterModel) {
        this.characterInList = item

        characterNameTV?.text = item.name
        characterImageIV?.setImageFromUrl(item.image)
    }
}