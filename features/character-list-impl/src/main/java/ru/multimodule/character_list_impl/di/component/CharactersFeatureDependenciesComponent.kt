package ru.multimodule.character_list_impl.di.component

import dagger.Component
import ru.multimodule.character_list_api.CharactersListNavigationApi
import ru.multimodule.character_list_impl.di.dependencies.CharacterListFeatureDependencies
import ru.multimodule.dagger.PerFeature
import ru.multimodule.database_api.DatabaseInteractionApi
import ru.multimodule.network_api.NetworkApi


@PerFeature
@Component(
    dependencies = [
        NetworkApi::class,
        DatabaseInteractionApi::class,
        CharactersListNavigationApi::class
    ]
)
interface CharactersFeatureDependenciesComponent: CharacterListFeatureDependencies