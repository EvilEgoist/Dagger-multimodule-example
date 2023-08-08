package ru.multimodule.character_detail_impl.di.component

import dagger.Component
import ru.multimodule.character_detail_api.CharacterDetailNavigationApi
import ru.multimodule.character_detail_impl.di.dependencies.CharacterDetailFeatureDependencies
import ru.multimodule.dagger.PerFeature
import ru.multimodule.database_api.DatabaseInteractionApi

@PerFeature
@Component(
    dependencies = [
        DatabaseInteractionApi::class,
        CharacterDetailNavigationApi::class
    ]
)
interface CharacterDetailFeatureDependenciesComponent : CharacterDetailFeatureDependencies