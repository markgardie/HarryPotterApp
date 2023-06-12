package com.example.harrypotterapp.data.mappers

import androidx.lifecycle.MediatorLiveData
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

    fun mapListDtoToEntity(list: List<CharacterDto>) =
        list.map {
            mapDtoToEntity(it)
        }
}