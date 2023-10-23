package com.example.harrypotterapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spells")
data class SpellDbModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val isFavorite: Boolean = false
)
