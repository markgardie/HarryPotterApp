package com.example.harrypotterapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterDbModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val species: String,
    val gender: String,
    val house: String,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val patronus: String,
    val image: String,
    val isFavorite: Boolean = false
)