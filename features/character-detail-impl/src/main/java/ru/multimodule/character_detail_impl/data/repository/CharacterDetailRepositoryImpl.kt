package ru.multimodule.character_detail_impl.data.repository

import android.util.Log
import ru.multimodule.character_detail_impl.data.mapper.mapToModel
import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel
import ru.multimodule.character_detail_impl.domain.repository.CharacterDetailRepository
import ru.multimodule.database_api.DatabaseInteractionApi
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val databaseInteractionApi: DatabaseInteractionApi
): CharacterDetailRepository {

    override suspend fun getCharacterDetail(characterId: Int): CharacterDetailModel {
        val result = databaseInteractionApi.getSingleCharacter(characterId).mapToModel()
        Log.d("CharacterDetailRepositoryImpl", "getCharacterDetail: $result")
        return result
    }
}