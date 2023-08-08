package ru.multimodule.character_list_impl.domain.usecase

import ru.multimodule.character_list_impl.domain.model.CharactersCallResultWrapper
import ru.multimodule.character_list_impl.domain.model.CharacterModel

interface GetCharactersUseCase {

    suspend fun getCharactersList(): CharactersCallResultWrapper<List<CharacterModel>>
}