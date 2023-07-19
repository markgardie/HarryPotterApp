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

    suspend fun upsertCharacters(characters: List<CharacterDbModel>) {
        characterDao.upsertCharacters(characters)
    }

    suspend fun updateFavoriteCharacter(character: CharacterDbModel) {
        characterDao.updateFavoriteCharacter(character)
    }

    fun getSpellStream() = spellDao.getSpellsStream()

    suspend fun upsertSpells(spells: List<SpellDbModel>) {
        spellDao.upsertSpells(spells)
    }

    suspend fun updateFavoriteSpell(spell: SpellDbModel) {
        spellDao.updateFavoriteSpell(spell)
    }

}