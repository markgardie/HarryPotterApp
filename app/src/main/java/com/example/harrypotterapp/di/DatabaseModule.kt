package com.example.harrypotterapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harrypotterapp.data.database.CharacterDao
import com.example.harrypotterapp.data.database.Database
import com.example.harrypotterapp.data.database.SpellDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseInstance(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: Database): CharacterDao {
        return database.characterDao()
    }

    @Singleton
    @Provides
    fun provideSpellDao(database: Database): SpellDao {
        return database.spellDao()
    }

    private const val DATABASE_NAME = "app_db"
}