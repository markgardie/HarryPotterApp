package com.example.harrypotterapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("select * from characters")
    fun getCharactersStream(): Flow<List<CharacterDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacters(characters: List<CharacterDbModel>)

}