package com.example.harrypotterapp.data.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET

interface CharacterApi {

    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterDto>
}