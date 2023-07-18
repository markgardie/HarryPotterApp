package com.example.harrypotterapp.domain.repositories

import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.models.SpellEntity
import kotlinx.coroutines.flow.Flow

interface SpellRepository {

    fun getSpellsStream(): Flow<List<SpellEntity>>

    suspend fun refresh()

}