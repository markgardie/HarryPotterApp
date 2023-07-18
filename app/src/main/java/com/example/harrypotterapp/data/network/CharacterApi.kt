package com.example.harrypotterapp.data.network

import retrofit2.http.GET

interface CharacterApi {

    @GET("characters")
    suspend fun fetchCharacters(): List<CharacterDto>
}