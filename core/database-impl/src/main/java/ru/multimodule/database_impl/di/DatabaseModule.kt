package ru.multimodule.database_impl.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
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
}