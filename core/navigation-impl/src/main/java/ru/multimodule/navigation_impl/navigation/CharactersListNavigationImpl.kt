package ru.multimodule.navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.multimodule.character_list_api.CharactersListNavigationApi
import ru.multimodule.character_list_impl.presentation.view.CharactersFragment
import javax.inject.Inject

class CharactersListNavigationImpl @Inject constructor(): CharactersListNavigationApi {

    override fun isClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != CharactersFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(CharactersFragment::class.java.simpleName) == null
        } else {
            true
        }
    }

    override fun navigateToDetail(fragment: Fragment, charId: Int) {
//        val newFragment = CharacterDetailFragment.newInstance(charId)
//        fragment.activity
//            ?.supportFragmentManager
//            ?.beginTransaction()
//            ?.replace(R.id.fragmentContainerVIew, newFragment, CharacterDetailFragment::class.java.simpleName)
//            ?.addToBackStack(null)
//            ?.commit()
    }
}