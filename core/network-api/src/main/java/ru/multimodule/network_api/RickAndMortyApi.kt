package ru.multimodule.network_api

import retrofit2.http.GET
import ru.multimodule.network_api.model.CharactersResponse
import ru.multimodule.network_api.model.ResponseErrors

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): ServerResponse<CharactersResponse, ResponseErrors>

}