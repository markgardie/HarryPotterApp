package com.example.harrypotterapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import com.example.harrypotterapp.domain.repositories.ConnectivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(CharactersUiState(isLoading = true))
    private val _characters = characterRepository.getCharactersStream()

    val uiState = combine(
        _loading,
        _characters
    ) { _, characters ->
        CharactersUiState(
            isLoading = false,
            characters = characters
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CharactersUiState()
    )


    fun updateFavoriteCharacter(characterEntity: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.updateFavoriteCharacter(characterEntity)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            if (connectivityRepository.hasInternetConnection()) characterRepository.refresh()
        }

    }

}

data class CharactersUiState(
    val isLoading: Boolean = false,
    val characters: List<CharacterEntity> = emptyList()
)