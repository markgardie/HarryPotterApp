package com.example.harrypotterapp.data.datasources

import com.example.harrypotterapp.data.database.CharacterDao
import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.database.SpellDao
import com.example.harrypotterapp.data.database.SpellDbModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val spellDao: SpellDao
) {

    fun getCharacterStream() = characterDao.getCharactersStream()

    suspend fun updateCharacters(characters: List<CharacterDbModel>) {
        characterDao.updateCharacters(characters)
    }

    fun getSpellStream() = spellDao.getSpellsStream()

    suspend fun updateSpells(spells: List<SpellDbModel>) {
        spellDao.updateSpells(spells)
    }

}