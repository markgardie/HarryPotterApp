package com.example.harrypotterapp.domain.usecases

import com.example.harrypotterapp.domain.models.SpellEntity
import com.example.harrypotterapp.domain.repositories.SpellRepository
import javax.inject.Inject

class CacheSpellsUseCase @Inject constructor(
    private val repository: SpellRepository
) {

    suspend operator fun invoke(spells: List<SpellEntity>) {
        repository.cacheSpells(spells)
    }
}