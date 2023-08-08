package ru.multimodule.character_list_impl.domain.usecase

import ru.multimodule.character_list_impl.domain.model.CharactersCallResultWrapper
import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.domain.repository.CharactersRepository
import javax.inject.Inject


class GetCharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    GetCharactersUseCase {

    override suspend fun getCharactersList(): CharactersCallResultWrapper<List<CharacterModel>> {
        return repository.getCharacters()
    }
}