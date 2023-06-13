package com.example.harrypotterapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.domain.usecases.CacheCharactersUseCase
import com.example.harrypotterapp.domain.usecases.GetAllCharactersUseCase
import com.example.harrypotterapp.domain.usecases.GetCachedCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCachedCharactersUseCase: GetCachedCharactersUseCase,
    private val gelAllCharactersUseCase: GetAllCharactersUseCase,
    private val cacheCharactersUseCase: CacheCharactersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CharacterState>()
    val state: LiveData<CharacterState>
        get() = _state


    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.value = CharacterState.Loading

            try {
                val characters = gelAllCharactersUseCase()
                _state.value = CharacterState.Success(characters)
                cacheCharactersUseCase(characters.subList(0, 9))

            } catch (e: Exception) {
                _state.value = CharacterState.Error("Characters not found")
            }
        }
    }

    fun getCachedCharacters() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = getCachedCharactersUseCase()
                _state.value = CharacterState.Success(characters)

            } catch (e: Exception) {
                _state.value = CharacterState.Error("Characters not found")
            }
        }


    }



}