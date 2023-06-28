package com.example.harrypotterapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.SpellEntity

@Dao
interface SpellDao {

    @Query("select * from spells")
    suspend fun getCachedSpells(): List<SpellDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheSpells(spells: List<SpellDbModel>)
}