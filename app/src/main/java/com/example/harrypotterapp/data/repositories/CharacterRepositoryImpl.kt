package com.example.harrypotterapp.data.repositories

import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.datasources.LocalDataSource
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.asDbModel
import com.example.harrypotterapp.data.mappers.asEntity
import com.example.harrypotterapp.data.network.CharacterDto
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): CharacterRepository {

    override fun getCharactersStream(): Flow<List<CharacterEntity>> =
        localDataSource.getCharacterStream()
            .map { it.map(CharacterDbModel::asEntity) }

    override suspend fun refresh() {
        val characters = remoteDataSource.fetchCharacters()
        localDataSource.upsertCharacters(characters.map(CharacterDto::asDbModel))
    }

    override suspend fun updateFavoriteCharacter(characterEntity: CharacterEntity) {
        localDataSource.updateFavoriteCharacter(characterEntity.asDbModel())
    }
}