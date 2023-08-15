package ru.multimodule.character_detail_impl.domain.usecase

import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel
import ru.multimodule.character_detail_impl.domain.repository.CharacterDetailRepository
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(private val repository: CharacterDetailRepository) :
    GetCharacterDetailUseCase {

    override suspend fun getCharacterDetail(characterId: Int): CharacterDetailModel {
        return repository.getCharacterDetail(characterId)
    }
}