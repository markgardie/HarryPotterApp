package com.example.harrypotterapp.data.database

import androidx.room.*
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.SpellEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {

    @Query("select * from spells")
    fun getSpellsStream(): Flow<List<SpellDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSpells(spells: List<SpellDbModel>)
}