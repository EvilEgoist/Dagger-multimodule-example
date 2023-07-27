package ru.multimodule.character_list_impl.domain.repository

import ru.multimodule.character_list_impl.domain.model.CharacterModel

interface CharactersRepository {

    fun getCharacters(): List<CharacterModel>

}