package ru.multimodule.navigation_impl.di

import android.content.Context
import ru.multimodule.character_detail_impl.di.component.CharacterDetailFeatureComponent
import ru.multimodule.character_detail_impl.di.component.DaggerCharacterDetailFeatureDependenciesComponent
import ru.multimodule.character_list_impl.di.component.CharactersListFeatureComponent
import ru.multimodule.character_list_impl.di.component.DaggerCharactersFeatureDependenciesComponent
import ru.multimodule.database_impl.di.DaggerDatabaseComponent
import ru.multimodule.database_impl.di.DatabaseComponent
import ru.multimodule.network_impl.di.DaggerNetworkComponent
import ru.multimodule.network_impl.di.NetworkComponent

object FeatureInjectorProxy {

    fun initChararactersListFeatureDi(appContext: Context){
        CharactersListFeatureComponent.initAndGet(
            DaggerCharactersFeatureDependenciesComponent.builder()
                .networkApi(DaggerNetworkComponent.factory().create())
                .charactersListNavigationApi(CoreNavigationComponent.initAndGet()?.getCharactersListNavigation())
                .databaseInteractionApi(DatabaseComponent.initAndGet(appContext)?.dao())
                .build()
        )
    }

    fun initCharacterDetailFeatureDi(appContext: Context){
        CharacterDetailFeatureComponent.initAndGet(
            DaggerCharacterDetailFeatureDependenciesComponent.builder()
                .databaseInteractionApi(DatabaseComponent.initAndGet(appContext)?.dao())
                .characterDetailNavigationApi(CoreNavigationComponent.initAndGet()?.getCharacterDetailNavigation())
                .build()
        )
    }

}