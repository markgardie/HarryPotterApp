package com.example.harrypotterapp.domain.repositories

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.domain.models.CharacterEntity

interface CharacterRepository {

    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    suspend fun cacheCharacters(characters: List<CharacterEntity>)

    fun getCachedCharacters(): LiveData<List<CharacterEntity>>

}