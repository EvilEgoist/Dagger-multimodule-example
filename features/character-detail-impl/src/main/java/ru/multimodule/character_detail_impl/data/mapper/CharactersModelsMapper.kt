package ru.multimodule.character_detail_impl.data.mapper

import ru.multimodule.character_detail_impl.domain.model.CharacterDetailModel
import ru.multimodule.database_api.model.CharacterEntity
import ru.multimodule.network_api.model.CharacterDto

fun CharacterEntity.mapToModel(): CharacterDetailModel {
    return CharacterDetailModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        image = this.image
    )
}
