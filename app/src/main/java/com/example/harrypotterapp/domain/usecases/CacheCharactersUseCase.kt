package com.example.harrypotterapp.domain.usecases

import com.example.harrypotterapp.domain.models.Character
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class CacheCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(characters: List<Character>) {
        repository.cacheCharacters(characters)
    }
}