package com.example.harrypotterapp.presentation.viewmodels

import com.example.harrypotterapp.domain.models.CharacterEntity

sealed class CharacterState {

    object Loading: CharacterState()

    data class Success(val data: List<CharacterEntity>): CharacterState()

    data class Error(val message: String): CharacterState()
}