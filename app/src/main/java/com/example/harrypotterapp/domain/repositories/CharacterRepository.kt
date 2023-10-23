package com.example.harrypotterapp.domain.repositories

import com.example.harrypotterapp.domain.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersStream(): Flow<List<CharacterEntity>>

    suspend fun refresh()

    suspend fun updateFavoriteCharacter(characterEntity: CharacterEntity)

}