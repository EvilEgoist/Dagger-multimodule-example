package ru.multimodule.character_list_impl.di.modules

import dagger.Module
import dagger.Provides
import ru.multimodule.character_list_impl.domain.repository.CharactersRepository
import ru.multimodule.character_list_impl.domain.usecase.GetCharactersUseCase
import ru.multimodule.character_list_impl.domain.usecase.getCharacters

@Module
interface UseCaseModule {

    companion object {

        @Provides
        fun provideGetCharactersUseCase(repository: CharactersRepository): GetCharactersUseCase {
            return GetCharactersUseCase {
                getCharacters(repository)
            }
        }
    }
}