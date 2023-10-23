package com.example.harrypotterapp.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {

    @Query("select * from spells")
    fun getSpellsStream(): Flow<List<SpellDbModel>>

    @Query("select * from spells")
    suspend fun getSpells(): List<SpellDbModel>

    @Upsert
    suspend fun upsertSpells(spells: List<SpellDbModel>)

    @Update
    suspend fun updateFavoriteSpell(spell: SpellDbModel)
}