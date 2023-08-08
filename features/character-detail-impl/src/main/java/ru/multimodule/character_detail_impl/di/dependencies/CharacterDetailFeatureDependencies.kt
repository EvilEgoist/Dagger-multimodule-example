package ru.multimodule.character_detail_impl.di.dependencies

import ru.multimodule.character_detail_api.CharacterDetailNavigationApi
import ru.multimodule.database_api.DatabaseInteractionApi

interface CharacterDetailFeatureDependencies{

    fun databaseInteractionApi(): DatabaseInteractionApi
    fun characterDetailNavigationApi(): CharacterDetailNavigationApi
}