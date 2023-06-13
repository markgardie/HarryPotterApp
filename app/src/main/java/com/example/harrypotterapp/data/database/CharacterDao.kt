package com.example.harrypotterapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("select * from characters")
    fun getCachedCharacters(): LiveData<List<CharacterDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheCharacters(characters: List<CharacterDbModel>)
}