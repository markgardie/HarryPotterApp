package com.example.harrypotterapp.data.mappers

import com.example.harrypotterapp.data.database.SpellDbModel
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.SpellEntity
import javax.inject.Inject

class SpellMapper @Inject constructor() {

    // Database
    private fun mapEntityToDbModel(spellEntity: SpellEntity) =
        SpellDbModel(
            id = spellEntity.id,
            name = spellEntity.name,
            description = spellEntity.description
        )

    private fun mapDbModelToEntity(spellDbModel: SpellDbModel) =
        SpellEntity(
            id = spellDbModel.id,
            name = spellDbModel.name,
            description = spellDbModel.description
        )

    fun mapListEntityToListDbModel(list: List<SpellEntity>) =
        list.map {
            mapEntityToDbModel(it)
        }

    fun mapListDbModelToListEntity(list: List<SpellDbModel>) =
        list.map {
            mapDbModelToEntity(it)
        }

    // Api

    private fun mapDtoToEntity(spellDto: SpellDto) =
        SpellEntity(
            id = spellDto.id,
            name = spellDto.name,
            description = spellDto.description
        )

    fun mapListDtoToListEntity(list: List<SpellDto>) =
        list.map {
            mapDtoToEntity(it)
        }
}