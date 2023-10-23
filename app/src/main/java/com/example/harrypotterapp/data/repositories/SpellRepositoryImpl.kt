package com.example.harrypotterapp.data.repositories

import com.example.harrypotterapp.data.database.SpellDbModel
import com.example.harrypotterapp.data.datasources.LocalDataSource
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.asDbModel
import com.example.harrypotterapp.data.mappers.asEntity
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.SpellEntity
import com.example.harrypotterapp.domain.repositories.SpellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SpellRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): SpellRepository {

    override fun getSpellsStream(): Flow<List<SpellEntity>> =
        localDataSource.getSpellStream()
            .map { it.map(SpellDbModel::asEntity) }

    override suspend fun refresh() {
        val spellsNetwork = remoteDataSource.fetchSpells().map(SpellDto::asDbModel)
        val spellsLocal = localDataSource.getSpells()

        val mergedSpells = mutableListOf<SpellDbModel>()

        mergedSpells.addAll(spellsNetwork)

        for (spell in spellsLocal) {

            val existingSpellIndex = mergedSpells.indexOfFirst { it.id == spell.id }

            if (existingSpellIndex != -1) {

                val existingSpell = mergedSpells[existingSpellIndex]
                val updatedSpell = existingSpell.copy(isFavorite = spell.isFavorite)
                mergedSpells[existingSpellIndex] = updatedSpell
            } else {

                mergedSpells.add(spell)
            }

        }

        localDataSource.upsertSpells(mergedSpells)
    }

    override suspend fun updateFavoriteSpell(spellEntity: SpellEntity) {
        localDataSource.updateFavoriteSpell(spellEntity.asDbModel())
    }
}