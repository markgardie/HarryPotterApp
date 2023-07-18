package com.example.harrypotterapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.domain.models.SpellEntity
import com.example.harrypotterapp.domain.repositories.ConnectivityRepository
import com.example.harrypotterapp.domain.repositories.SpellRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellViewModel @Inject constructor(
    private val spellRepository: SpellRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(SpellsUiState(isLoading = true))
    private val _spells = spellRepository.getSpellsStream()

    val uiState = combine(
        _loading,
        _spells
    ) { _, spells ->

        SpellsUiState(
            isLoading = false,
            spells = spells
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        SpellsUiState()
    )

    init {
        viewModelScope.launch {
            if(connectivityRepository.hasInternetConnection()) spellRepository.refresh()
        }
    }

}

data class SpellsUiState(
    val isLoading: Boolean = false,
    val spells: List<SpellEntity> = emptyList()
)