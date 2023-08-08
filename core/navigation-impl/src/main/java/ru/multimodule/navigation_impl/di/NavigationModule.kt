package ru.multimodule.navigation_impl.di

import dagger.Binds
import dagger.Module
import ru.multimodule.character_detail_api.CharacterDetailNavigationApi
import ru.multimodule.character_list_api.CharactersListNavigationApi
import ru.multimodule.navigation_impl.navigation.CharacterDetailNavigationImpl
import ru.multimodule.navigation_impl.navigation.CharactersListNavigationImpl

@Module
interface NavigationModule {

    @Binds
    fun bindCharactersListNavigation(navigation: CharactersListNavigationImpl): CharactersListNavigationApi

    @Binds
    fun bindCharacterDetailNavigation(navigation: CharacterDetailNavigationImpl): CharacterDetailNavigationApi
}