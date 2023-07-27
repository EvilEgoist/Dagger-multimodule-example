package ru.multimodule.character_list_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.multimodule.character_list_impl.data.repository.CharactersRepositoryImpl
import ru.multimodule.character_list_impl.domain.repository.CharactersRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository
}