package com.lealpy.marvelapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharactersDao {

    @Query("SELECT * FROM character_entities WHERE id = :characterId")
    fun getCharacterEntityById(characterId: Int): Single<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterEntities(toCharacterEntities: List<CharacterEntity>): Completable

}
