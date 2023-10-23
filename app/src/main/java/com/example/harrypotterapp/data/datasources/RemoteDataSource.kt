package com.example.harrypotterapp.data.datasources

import com.example.harrypotterapp.data.network.CharacterApi
import com.example.harrypotterapp.data.network.SpellApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val characterApi: CharacterApi,
    private val spellApi: SpellApi
) {

    suspend fun fetchCharacters() = characterApi.fetchCharacters()

    suspend fun fetchSpells() = spellApi.fetchSpells()

}