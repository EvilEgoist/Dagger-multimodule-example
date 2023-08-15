package ru.multimodule.navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.multimodule.character_detail_impl.presentation.view.CharacterDetailFragment
import ru.multimodule.character_list_api.CharactersListNavigationApi
import ru.multimodule.character_list_impl.presentation.view.CharactersFragment
import ru.multimodule.navigation_impl.R
import ru.multimodule.navigation_impl.di.FeatureInjectorProxy
import javax.inject.Inject

class CharactersListNavigationImpl @Inject constructor() : CharactersListNavigationApi {

    override fun isClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != CharactersFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(CharactersFragment::class.java.simpleName) == null
        } else {
            true
        }
    }

    override fun navigateToDetail(fragment: Fragment, charId: Int) {
        if (fragment.activity != null) {
            fragment.activity?.let {
                FeatureInjectorProxy.initCharacterDetailFeatureDi(it.applicationContext)
                val newFragment = CharacterDetailFragment.newInstance(charId)
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragmentContainerVIew,
                        newFragment as Fragment,
                        CharacterDetailFragment::class.java.simpleName
                    )
                    .addToBackStack(fragment::class.java.simpleName)
                    .commit()
            }
        } else {
            throw NullPointerException(
                "Fragment($fragment) activity is null," +
                        " fragment.activity=${fragment.activity}"
            )
        }
    }
}