package com.example.harrypotterapp.data.repositories

import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.database.SpellDbModel
import com.example.harrypotterapp.data.datasources.LocalDataSource
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.asDbModel
import com.example.harrypotterapp.data.mappers.asEntity
import com.example.harrypotterapp.data.network.CharacterDto
import com.example.harrypotterapp.data.network.SpellDto
import com.example.harrypotterapp.domain.models.CharacterEntity
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
        val spells = remoteDataSource.fetchSpells()
        localDataSource.updateSpells(spells.map(SpellDto::asDbModel))
    }
}