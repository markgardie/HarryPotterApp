package com.example.harrypotterapp.data.network


data class CharacterDto(
    val id: String,
    val name: String,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val patronus: String,
    val image: String
)