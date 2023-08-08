package ru.multimodule.character_list_impl.di.dependencies

import ru.multimodule.character_list_api.CharactersListNavigationApi
import ru.multimodule.database_api.DatabaseInteractionApi
import ru.multimodule.network_api.RickAndMortyApi

interface CharacterListFeatureDependencies {
    fun rickAndMortyApi(): RickAndMortyApi
    fun databaseInteractionApi(): DatabaseInteractionApi
    fun charactersListNavigationApi(): CharactersListNavigationApi
}