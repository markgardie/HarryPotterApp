package com.example.harrypotterapp.data.mappers

import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.network.CharacterDto
import javax.inject.Inject
import com.example.harrypotterapp.domain.models.CharacterEntity

class CharacterMapper @Inject constructor() {

    fun mapDtoToEntity(characterDto: CharacterDto) =
        CharacterEntity(
            id = characterDto.id,
            name = characterDto.name,
            species = characterDto.species,
            gender = characterDto.gender,
            house = characterDto.house,
            ancestry = characterDto.ancestry,
            eyeColour = characterDto.eyeColour,
            hairColour = characterDto.hairColour,
            patronus = characterDto.patronus,
            image = characterDto.image
        )

    fun mapListDtoToListEntity(list: List<CharacterDto>) =
        list.map {
            mapDtoToEntity(it)
        }

    fun mapEntityToDbModel(character: CharacterEntity) =
        CharacterDbModel(
            id = character.id,
            name = character.name,
            species = character.species,
            gender = character.gender,
            house = character.house,
            ancestry = character.ancestry,
            eyeColour = character.eyeColour,
            hairColour = character.hairColour,
            patronus = character.patronus,
            image = character.image
        )

    fun mapListEntityToListDbModel(list: List<CharacterEntity>) =
        list.map {
            mapEntityToDbModel(it)
        }


    fun mapDbModelToEntity(characterDbModel: CharacterDbModel) =
        CharacterEntity(
            id = characterDbModel.id,
            name = characterDbModel.name,
            species = characterDbModel.species,
            gender = characterDbModel.gender,
            house = characterDbModel.house,
            ancestry = characterDbModel.ancestry,
            eyeColour = characterDbModel.eyeColour,
            hairColour = characterDbModel.hairColour,
            patronus = characterDbModel.patronus,
            image = characterDbModel.image
        )

    fun mapListDbModelToListEntity(list: List<CharacterDbModel>) =
        list.map {
            mapDbModelToEntity(it)
        }
}