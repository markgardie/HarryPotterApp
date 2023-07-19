package com.example.harrypotterapp.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("select * from characters")
    fun getCharactersStream(): Flow<List<CharacterDbModel>>

    @Upsert
    suspend fun upsertCharacters(characters: List<CharacterDbModel>)

    @Update
    suspend fun updateFavoriteCharacter(character: CharacterDbModel)

}