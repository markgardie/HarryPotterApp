package com.example.harrypotterapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterEntity(
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
): Parcelable