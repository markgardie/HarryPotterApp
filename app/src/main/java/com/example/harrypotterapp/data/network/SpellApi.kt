package com.example.harrypotterapp.data.network

import retrofit2.http.GET

interface SpellApi {

    @GET("spells")
    suspend fun fetchSpells(): List<SpellDto>
}