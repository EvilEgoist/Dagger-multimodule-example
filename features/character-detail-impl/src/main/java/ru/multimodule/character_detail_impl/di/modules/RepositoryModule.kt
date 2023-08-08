package ru.multimodule.character_detail_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.multimodule.character_detail_impl.data.repository.CharacterDetailRepositoryImpl
import ru.multimodule.character_detail_impl.domain.repository.CharacterDetailRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: CharacterDetailRepositoryImpl): CharacterDetailRepository
}