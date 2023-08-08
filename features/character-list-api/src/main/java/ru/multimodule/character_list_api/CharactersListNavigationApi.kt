package ru.multimodule.character_list_api

import androidx.fragment.app.Fragment

interface CharactersListNavigationApi {

    fun isClosed(fragment: Fragment): Boolean

    fun navigateToDetail(fragment: Fragment, charId: Int)
}