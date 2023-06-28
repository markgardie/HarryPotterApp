package com.example.harrypotterapp.presentation.viewmodels

import com.example.harrypotterapp.domain.models.SpellEntity

sealed class SpellState {

    object Loading : SpellState()

    data class Success(val data: List<SpellEntity>) : SpellState()

    data class Error(val message: String) : SpellState()
}