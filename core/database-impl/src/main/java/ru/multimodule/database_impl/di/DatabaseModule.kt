package ru.multimodule.database_impl.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.multimodule.database_api.DatabaseInteractionApi
import ru.multimodule.database_impl.CharactersDao
import ru.multimodule.database_impl.RickAndMortyDatabase
import javax.inject.Singleton

@Module
interface DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): RickAndMortyDatabase {
            return Room.databaseBuilder(
                context,
                RickAndMortyDatabase::class.java,
                "rickAndMorty-database"
            ).build()
        }
    }

    @Binds
    fun bindDatabaseInteractionApi(charactersDao: CharactersDao): DatabaseInteractionApi
}