package ru.multimodule.character_list_impl.data.mapper

import ru.multimodule.character_list_impl.domain.model.CharacterModel
import ru.multimodule.database_api.model.CharacterEntity
import ru.multimodule.network_api.model.CharacterDto

fun CharacterDto.mapToEntity(): CharacterEntity{
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        image = this.image
    )
}

fun CharacterEntity.mapToModel(): CharacterModel {
    return CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        image = this.image
    )
}
