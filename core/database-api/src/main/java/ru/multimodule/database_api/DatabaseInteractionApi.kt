package ru.multimodule.database_api

import ru.multimodule.database_api.model.CharacterEntity

interface DatabaseInteractionApi {

    fun getCharacters(): List<CharacterEntity>

    fun insertCharacters(characterList: List<CharacterEntity>)

    fun getSingleCharacter(id: Int): CharacterEntity
}