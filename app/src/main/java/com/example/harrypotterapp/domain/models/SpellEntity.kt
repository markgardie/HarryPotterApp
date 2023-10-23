package com.example.harrypotterapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpellEntity(
    val id: String,
    val name: String,
    val description: String,
    val isFavorite: Boolean = false
): Parcelable
