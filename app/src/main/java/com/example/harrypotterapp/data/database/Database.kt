package com.example.harrypotterapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterDbModel::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}