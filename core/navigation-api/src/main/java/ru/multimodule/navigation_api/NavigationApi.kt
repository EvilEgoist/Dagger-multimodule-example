package ru.multimodule.navigation_api

import ru.multimodule.character_detail_api.CharacterDetailNavigationApi
import ru.multimodule.character_list_api.CharactersListNavigationApi

interface NavigationApi {

    fun getCharacterDetailNavigation(): CharacterDetailNavigationApi

    fun getCharactersListNavigation(): CharactersListNavigationApi
}