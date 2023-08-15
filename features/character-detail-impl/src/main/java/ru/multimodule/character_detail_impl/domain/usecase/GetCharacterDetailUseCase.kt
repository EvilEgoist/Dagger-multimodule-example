package ru.multimodule.character_detail_impl.domain.usecase

import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel

interface GetCharacterDetailUseCase {

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailModel
}