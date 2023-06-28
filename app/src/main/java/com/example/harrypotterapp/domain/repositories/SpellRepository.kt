package com.example.harrypotterapp.domain.repositories

import com.example.harrypotterapp.domain.models.SpellEntity

interface SpellRepository {

    suspend fun getAllSpells(): List<SpellEntity>

    suspend fun getCachedSpells(): List<SpellEntity>

    suspend fun cacheSpells(spells: List<SpellEntity>)
}