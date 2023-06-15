package com.example.harrypotterapp.domain.repositories

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.domain.models.CharacterEntity

interface CharacterRepository {

    suspend fun getAllCharacters(): List<CharacterEntity>

    suspend fun cacheCharacters(characters: List<CharacterEntity>)

    suspend fun getCachedCharacters(): List<CharacterEntity>

}