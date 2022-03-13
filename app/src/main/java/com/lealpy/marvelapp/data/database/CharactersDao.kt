package com.lealpy.marvelapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersDao {

    @Query("SELECT * FROM character_entities WHERE id = :characterId")
    suspend fun getCharacterEntityById(characterId: Int): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterEntities(toCharacterEntities: List<CharacterEntity>)

}
