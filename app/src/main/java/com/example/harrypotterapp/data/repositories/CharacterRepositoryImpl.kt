package com.example.harrypotterapp.data.repositories

import androidx.lifecycle.MediatorLiveData
import com.example.harrypotterapp.data.datasources.LocalDataSource
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.CharacterMapper
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val characterMapper: CharacterMapper
): CharacterRepository {


    override suspend fun getAllCharacters() = characterMapper.mapListDtoToListEntity(
        remoteDataSource.getAllCharacters()
    )

    override suspend fun cacheCharacters(characters: List<CharacterEntity>) {
        localDataSource.cacheCharacters(
            characterMapper.mapListEntityToListDbModel(characters)
        )
    }

    override suspend fun getCachedCharacters() = characterMapper.mapListDbModelToListEntity(
        localDataSource.getCachedCharacters()
    )

}