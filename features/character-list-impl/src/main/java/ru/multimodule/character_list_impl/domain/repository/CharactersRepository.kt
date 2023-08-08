package ru.multimodule.character_list_impl.domain.repository

import ru.multimodule.character_list_impl.domain.model.CharactersCallResultWrapper
import ru.multimodule.character_list_impl.domain.model.CharacterModel

interface CharactersRepository {

    suspend fun getCharacters(): CharactersCallResultWrapper<List<CharacterModel>>
}