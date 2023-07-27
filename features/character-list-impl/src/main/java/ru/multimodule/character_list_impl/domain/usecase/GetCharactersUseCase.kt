package ru.multimodule.character_list_impl.domain.usecase

import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.domain.repository.CharactersRepository


fun interface GetCharactersUseCase: () -> List<CharacterModel>

fun getCharacters(repository: CharactersRepository): List<CharacterModel>{
    return repository.getCharacters()
}