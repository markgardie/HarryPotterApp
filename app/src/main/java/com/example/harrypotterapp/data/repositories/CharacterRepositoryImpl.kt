package com.example.harrypotterapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.harrypotterapp.data.datasources.RemoteDataSource
import com.example.harrypotterapp.data.mappers.CharacterMapper
import com.example.harrypotterapp.domain.models.Character
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val characterMapper: CharacterMapper
): CharacterRepository {


    override fun getAllCharacters() = MediatorLiveData<List<Character>>().apply {
        addSource(remoteDataSource.getAllCharacters()) {
            value = characterMapper.mapListDtoToEntity(it)
        }
    }

    override suspend fun cacheCharacters(characters: List<Character>) {
        TODO("Not yet implemented")
    }

    override fun getCachedCharacters(): LiveData<List<Character>> {
        TODO("Not yet implemented")
    }
}