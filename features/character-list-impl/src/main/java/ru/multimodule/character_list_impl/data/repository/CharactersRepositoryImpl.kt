package ru.multimodule.character_list_impl.data.repository

import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(): CharactersRepository {

    override fun getCharacters(): List<CharacterModel> {
        TODO("Not yet implemented")
    }
}