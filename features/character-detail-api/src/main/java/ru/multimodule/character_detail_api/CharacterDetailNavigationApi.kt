package ru.multimodule.character_detail_api

import androidx.fragment.app.Fragment

interface CharacterDetailNavigationApi {

    fun isClosed(fragment: Fragment): Boolean
}