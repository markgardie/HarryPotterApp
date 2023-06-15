package com.example.harrypotterapp.data.datasources

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.data.network.CharacterApi
import com.example.harrypotterapp.data.network.CharacterDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val characterApi: CharacterApi
) {

    suspend fun getAllCharacters(): List<CharacterDto> {
        return characterApi.getAllCharacters()
    }
}