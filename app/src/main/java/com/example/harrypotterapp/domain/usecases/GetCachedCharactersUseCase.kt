package com.example.harrypotterapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.harrypotterapp.domain.models.Character
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCachedCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(): LiveData<List<Character>> {
        return repository.getCachedCharacters()
    }
}