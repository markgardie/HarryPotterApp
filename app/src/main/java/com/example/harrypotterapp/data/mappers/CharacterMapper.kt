package com.example.harrypotterapp.data.mappers

import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.network.CharacterDto
import javax.inject.Inject
import com.example.harrypotterapp.domain.models.Character

class CharacterMapper @Inject constructor() {

    fun mapDtoToEntity(characterDto: CharacterDto) =
        Character(
            id = characterDto.id,
            name = characterDto.name,
            species = characterDto.species,
            gender = characterDto.gender,
            house = characterDto.house,
            dateOfBirth = characterDto.dateOfBirth,
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

    fun mapEntityToDbModel(character: Character) =
        CharacterDbModel(
            id = character.id,
            name = character.name,
            species = character.species,
            gender = character.gender,
            house = character.house,
            dateOfBirth = character.dateOfBirth,
            ancestry = character.ancestry,
            eyeColour = character.eyeColour,
            hairColour = character.hairColour,
            patronus = character.patronus,
            image = character.image
        )

    fun mapListEntityToListDbModel(list: List<Character>) =
        list.map {
            mapEntityToDbModel(it)
        }


    fun mapDbModelToEntity(characterDbModel: CharacterDbModel) =
        Character(
            id = characterDbModel.id,
            name = characterDbModel.name,
            species = characterDbModel.species,
            gender = characterDbModel.gender,
            house = characterDbModel.house,
            dateOfBirth = characterDbModel.dateOfBirth,
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