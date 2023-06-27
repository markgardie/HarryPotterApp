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
import com.example.harrypotterapp.domain.usecases.CacheSpellsUseCase
import com.example.harrypotterapp.domain.usecases.GetAllSpellsUseCase
import com.example.harrypotterapp.domain.usecases.GetCachedSpellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SpellViewModel @Inject constructor(
    private val getAllSpellsUseCase: GetAllSpellsUseCase,
    private val getCachedSpellsUseCase: GetCachedSpellsUseCase,
    private val cacheSpellsUseCase: CacheSpellsUseCase,
    application: Application
): AndroidViewModel(application) {

    private val _state = MutableLiveData<SpellState>()
    val state: LiveData<SpellState> = _state

    init {
        getSpells()
    }

    private fun getSpells() {
        if (hasInternetConnection()) {
            getAllSpells()
        }
        else {
            getCachedSpells()
        }
    }

    private fun getAllSpells() {
        viewModelScope.launch {

            _state.value = SpellState.Loading

            try {
                val spells = getAllSpellsUseCase()
                cacheSpellsUseCase(spells.subList(0, 9))
                _state.value = SpellState.Success(spells)

            } catch (e: Exception) {
                _state.value = SpellState.Error("Spells not found")
                Log.d("SpellViewModel", "$e")

            }
        }
    }

    private fun getCachedSpells() {

        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                _state.value = SpellState.Loading
            }

            try {
                val spells = getCachedSpellsUseCase()
                withContext(Dispatchers.Main) {
                    _state.value = SpellState.Success(spells)

                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _state.value = SpellState.Error("Spells not found")
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