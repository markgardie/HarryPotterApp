package com.example.harrypotterapp.di

import com.example.harrypotterapp.data.repositories.CharacterRepositoryImpl
import com.example.harrypotterapp.domain.repositories.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
}