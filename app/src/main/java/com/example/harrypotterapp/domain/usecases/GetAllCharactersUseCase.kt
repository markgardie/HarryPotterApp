package com.example.harrypotterapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(): List<CharacterEntity> {
        return repository.getAllCharacters()
    }
}