package com.example.harrypotterapp.domain.repositories

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.domain.models.Character

interface CharacterRepository {

    fun getAllCharacters(): LiveData<List<Character>>

    suspend fun cacheCharacters(characters: List<Character>)

    fun getCachedCharacters(): LiveData<List<Character>>

}