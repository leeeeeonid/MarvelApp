package com.lealpy.marvelapp.domain.repositories

import com.lealpy.marvelapp.domain.models.Character

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(characterId: Int): Character

}
