package ru.multimodule.character_list_impl.data.repository

import ru.multimodule.character_list_impl.domain.model.CharactersCallResultWrapper
import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.character_list_impl.data.mapper.mapToEntity
import ru.multimodule.character_list_impl.data.mapper.mapToModel
import ru.multimodule.character_list_impl.domain.repository.CharactersRepository
import ru.multimodule.database_api.DatabaseInteractionApi
import ru.multimodule.network_api.RickAndMortyApi
import ru.multimodule.network_api.ServerResponse
import ru.multimodule.network_api.getErrorMessage
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val databaseInteractionApi: DatabaseInteractionApi
) : CharactersRepository {

    override suspend fun getCharacters(): CharactersCallResultWrapper<List<CharacterModel>> {
        val response = rickAndMortyApi.getCharacters()
        when (response) {
            is ServerResponse.Success -> {
                databaseInteractionApi.insertCharacters(response.body.results.map { it.mapToEntity() })
                return CharactersCallResultWrapper.Success(
                    characterList = databaseInteractionApi.getCharacters().map { it.mapToModel() }
                )
            }
            else -> {
                val localList = databaseInteractionApi.getCharacters().map { it.mapToModel() }
                val errorMes = response.getErrorMessage()
                return if (localList.isNotEmpty()){
                    CharactersCallResultWrapper.Error(localList, errorMes)
                } else {
                    CharactersCallResultWrapper.Error(message = errorMes)
                }
            }
        }
    }
}