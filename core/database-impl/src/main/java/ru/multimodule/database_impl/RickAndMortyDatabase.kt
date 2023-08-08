package ru.multimodule.database_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.multimodule.database_api.model.CharacterEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        CharacterEntity::class
    ],
    version = 1
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
}