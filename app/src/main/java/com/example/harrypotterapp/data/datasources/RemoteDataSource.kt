package com.example.harrypotterapp.data.datasources

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.data.network.CharacterApi
import com.example.harrypotterapp.data.network.CharacterDto
import com.example.harrypotterapp.data.network.SpellApi
import com.example.harrypotterapp.data.network.SpellDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val characterApi: CharacterApi,
    private val spellApi: SpellApi
) {

    suspend fun getAllCharacters(): List<CharacterDto> {
        return characterApi.getAllCharacters()
    }

    suspend fun getAllSpells(): List<SpellDto> {
        return spellApi.getAllSpells()
    }
}