package com.lealpy.marvelapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        CharacterEntity::class
    ],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
