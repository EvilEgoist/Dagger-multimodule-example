package ru.multimodule.character_detail_impl.domain.repository

import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel

interface CharacterDetailRepository {

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailModel
}