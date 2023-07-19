package com.example.harrypotterapp.data.repositories

import com.example.harrypotterapp.data.database.CharacterDbModel
import com.example.harrypotterapp.data.datasources.LocalDataSource
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.asDbModel
import com.example.harrypotterapp.data.mappers.asEntity
import com.example.harrypotterapp.data.network.CharacterDto
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): CharacterRepository {

    override fun getCharactersStream(): Flow<List<CharacterEntity>> =
        localDataSource.getCharacterStream()
            .map { it.map(CharacterDbModel::asEntity) }

    override suspend fun refresh() {
        val charactersNetwork = remoteDataSource.fetchCharacters().map(CharacterDto::asDbModel)
        val charactersLocal = localDataSource.getCharacters()

        val mergedCharacters = mutableListOf<CharacterDbModel>()

        mergedCharacters.addAll(charactersNetwork)

        for (character in charactersLocal) {

            val existingCharacterIndex = mergedCharacters.indexOfFirst { it.id == character.id }

            if (existingCharacterIndex != -1) {

                val existingCharacter = mergedCharacters[existingCharacterIndex]
                val updatedCharacter = existingCharacter.copy(isFavorite = character.isFavorite)
                mergedCharacters[existingCharacterIndex] = updatedCharacter
            } else {

                mergedCharacters.add(character)
            }

        }

        localDataSource.upsertCharacters(mergedCharacters)
    }

    override suspend fun updateFavoriteCharacter(characterEntity: CharacterEntity) {
        localDataSource.updateFavoriteCharacter(characterEntity.asDbModel())
    }
}