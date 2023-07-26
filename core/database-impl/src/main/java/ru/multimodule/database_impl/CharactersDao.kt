package ru.multimodule.database_impl

import androidx.room.Dao
import androidx.room.Query
import ru.multimodule.database_api.DatabaseInteractionApi
import ru.multimodule.database_api.model.CharacterEntity

@Dao
interface CharactersDao: DatabaseInteractionApi{

    @Query(
        "SELECT * FROM characters"
    )
    override fun getCharacters(): List<CharacterEntity>
}