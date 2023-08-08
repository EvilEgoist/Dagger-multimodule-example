package ru.multimodule.database_impl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.multimodule.database_api.DatabaseInteractionApi
import ru.multimodule.database_api.model.CharacterEntity

@Dao
interface CharactersDao: DatabaseInteractionApi {

    @Query(
        "SELECT * FROM characters"
    )
    override fun getCharacters(): List<CharacterEntity>

    @Query(
        "SELECT * FROM characters WHERE id = :id"
    )
    override fun getSingleCharacter(id: String): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertCharacters(characterList: List<CharacterEntity>)


}