package ru.multimodule.character_detail_impl.data.repository

import ru.multimodule.character_detail_impl.data.mapper.mapToModel
import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel
import ru.multimodule.character_detail_impl.domain.repository.CharacterDetailRepository
import ru.multimodule.database_api.DatabaseInteractionApi
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val databaseInteractionApi: DatabaseInteractionApi
): CharacterDetailRepository {

    override suspend fun getCharacterDetail(characterId: String): CharacterDetailModel {
        return databaseInteractionApi.getSingleCharacter(characterId).mapToModel()
    }
}