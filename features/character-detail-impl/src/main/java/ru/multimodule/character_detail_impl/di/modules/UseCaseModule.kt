package ru.multimodule.character_detail_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.multimodule.character_detail_impl.domain.usecase.GetCharacterDetailUseCase
import ru.multimodule.character_detail_impl.domain.usecase.GetCharacterDetailUseCaseImpl

@Module
interface UseCaseModule {

    @Binds
    fun bindGetCharacterDetailUseCase(impl: GetCharacterDetailUseCaseImpl): GetCharacterDetailUseCase
}