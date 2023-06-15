package com.example.harrypotterapp.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.domain.usecases.CacheCharactersUseCase
import com.example.harrypotterapp.domain.usecases.GetAllCharactersUseCase
import com.example.harrypotterapp.domain.usecases.GetCachedCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCachedCharactersUseCase: GetCachedCharactersUseCase,
    private val gelAllCharactersUseCase: GetAllCharactersUseCase,
    private val cacheCharactersUseCase: CacheCharactersUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableLiveData<CharacterState>()
    val state: LiveData<CharacterState>
        get() = _state



    init {
        getCharacters()
    }

    private fun getCharacters() {
        if (hasInternetConnection()) {
            getAllCharacters()
        }
        else {
            getCachedCharacters()
        }
    }

    private fun getAllCharacters() {
        viewModelScope.launch {

            _state.value = CharacterState.Loading

            try {
                val characters = gelAllCharactersUseCase()
                cacheCharactersUseCase(characters.subList(0, 9))
                _state.value = CharacterState.Success(characters)

            } catch (e: Exception) {
                _state.value = CharacterState.Error("Characters not found")
                Log.d("CharacterViewModel", "$e")

            }
        }
    }

    private fun getCachedCharacters() {

        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                _state.value = CharacterState.Loading
            }

            try {
                val characters = getCachedCharactersUseCase()
                withContext(Dispatchers.Main) {
                    _state.value = CharacterState.Success(characters)

                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _state.value = CharacterState.Error("Characters not found")
                }

            }
        }


    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}