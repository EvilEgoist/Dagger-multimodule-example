package ru.multimodule.database_impl.di

import dagger.Module
import dagger.Provides
import ru.multimodule.database_impl.CharactersDao
import ru.multimodule.database_impl.RickAndMortyDatabase

@Module
interface DaosModule {

    companion object {
        @Provides
        fun provideCharacterDao(database: RickAndMortyDatabase): CharactersDao {
            return database.charactersDao()
        }
    }
}