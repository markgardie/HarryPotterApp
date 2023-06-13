package com.example.harrypotterapp.data.datasources

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.data.database.CharacterDao
import com.example.harrypotterapp.data.database.CharacterDbModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {

    suspend fun getCachedCharacters(): List<CharacterDbModel> {
        return characterDao.getCachedCharacters()
    }

    suspend fun cacheCharacters(characters: List<CharacterDbModel>) {
        characterDao.cacheCharacters(characters)
    }
}