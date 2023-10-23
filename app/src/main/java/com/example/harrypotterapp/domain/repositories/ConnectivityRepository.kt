package com.example.harrypotterapp.domain.repositories

import kotlinx.coroutines.flow.Flow

interface ConnectivityRepository {

    fun hasInternetConnection(): Boolean

}