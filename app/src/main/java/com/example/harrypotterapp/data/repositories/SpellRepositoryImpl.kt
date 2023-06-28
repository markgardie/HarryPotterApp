package com.example.harrypotterapp.data.repositories

import com.example.harrypotterapp.data.datasources.LocalDataSource
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.SpellMapper
import com.example.harrypotterapp.domain.models.SpellEntity
import com.example.harrypotterapp.domain.repositories.SpellRepository
import javax.inject.Inject

class SpellRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: SpellMapper
): SpellRepository {

    override suspend fun getAllSpells(): List<SpellEntity> {
        return mapper.mapListDtoToListEntity(remoteDataSource.getAllSpells())
    }

    override suspend fun getCachedSpells(): List<SpellEntity> {
        return mapper.mapListDbModelToListEntity(localDataSource.getCachedSpells())
    }

    override suspend fun cacheSpells(spells: List<SpellEntity>) {
        localDataSource.cacheSpells(mapper.mapListEntityToListDbModel(spells))
    }
}