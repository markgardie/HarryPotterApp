package com.example.harrypotterapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.domain.models.Character
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(): LiveData<List<Character>> {
        return repository.getAllCharacters()
    }
}