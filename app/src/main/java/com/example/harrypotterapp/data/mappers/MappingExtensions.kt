package com.example.harrypotterapp.data.mappers

import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.database.SpellDbModel
import com.example.harrypotterapp.data.network.CharacterDto
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.models.SpellEntity

fun CharacterDbModel.asEntity() = CharacterEntity(
    id = id,
    name = name,
    species = species,
    gender = gender,
    house = house,
    ancestry = ancestry,
    eyeColour = eyeColour,
    hairColour = hairColour,
    patronus = patronus,
    image = image,
    isFavorite = isFavorite
)

fun CharacterDto.asDbModel() = CharacterDbModel(
    id = id,
    name = name,
    species = species,
    gender = gender,
    house = house,
    ancestry = ancestry,
    eyeColour = eyeColour,
    hairColour = hairColour,
    patronus = patronus,
    image = image
)

fun CharacterEntity.asDbModel() = CharacterDbModel(
    id = id,
    name = name,
    species = species,
    gender = gender,
    house = house,
    ancestry = ancestry,
    eyeColour = eyeColour,
    hairColour = hairColour,
    patronus = patronus,
    image = image,
    isFavorite = isFavorite
)

fun SpellDbModel.asEntity() = SpellEntity(
    id = id,
    name = name,
    description = description,
    isFavorite = isFavorite
)

fun SpellDto.asDbModel() = SpellDbModel(
    id = id,
    name = name,
    description = description
)

fun SpellEntity.asDbModel() = SpellDbModel(
    id = id,
    name = name,
    description = description,
    isFavorite = isFavorite
)