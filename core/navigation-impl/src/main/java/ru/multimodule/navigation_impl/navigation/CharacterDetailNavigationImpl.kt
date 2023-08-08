package ru.multimodule.navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.multimodule.character_detail_api.CharacterDetailNavigationApi
import ru.multimodule.character_detail_impl.presentation.view.CharacterDetailFragment
import javax.inject.Inject

class CharacterDetailNavigationImpl @Inject constructor() : CharacterDetailNavigationApi {

    override fun isClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != CharacterDetailFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(CharacterDetailFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}