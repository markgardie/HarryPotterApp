package com.example.harrypotterapp.data.datasources

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.data.database.CharacterDao
import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.database.SpellDao
import com.example.harrypotterapp.data.database.SpellDbModel
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.SpellEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val spellDao: SpellDao
) {

    suspend fun getCachedCharacters(): List<CharacterDbModel> {
        return characterDao.getCachedCharacters()
    }

    suspend fun cacheCharacters(characters: List<CharacterDbModel>) {
        characterDao.cacheCharacters(characters)
    }

    suspend fun getCachedSpells(): List<SpellDbModel> {
        return spellDao.getCachedSpells()
    }

    suspend fun cacheSpells(spells: List<SpellDbModel>) {
        spellDao.cacheSpells(spells)
    }
}