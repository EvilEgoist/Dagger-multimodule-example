package ru.multimodule.character_list_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.multimodule.character_list_impl.domain.usecase.GetCharactersUseCase
import ru.multimodule.character_list_impl.domain.usecase.GetCharactersUseCaseImpl

@Module
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(getCharactersUseCaseImpl: GetCharactersUseCaseImpl): GetCharactersUseCase
}