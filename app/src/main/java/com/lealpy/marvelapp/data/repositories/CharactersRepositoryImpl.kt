package com.lealpy.marvelapp.data.repositories

import com.lealpy.marvelapp.data.api.CharactersApi
import com.lealpy.marvelapp.data.database.CharactersDao
import com.lealpy.marvelapp.data.utils.toCharacter
import com.lealpy.marvelapp.data.utils.toCharacterEntities
import com.lealpy.marvelapp.data.utils.toCharacters
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        val characters = charactersApi.getCharacters(limit = LIMIT_ITEMS)
            .data
            .results
            .toCharacters()
        insertCharactersToDb(characters)
        return characters
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        return charactersDao.getCharacterEntityById(characterId).toCharacter()
    }

    private suspend fun insertCharactersToDb(characters: List<Character>) {
        charactersDao.insertCharacterEntities(characters.toCharacterEntities())
    }

    companion object {
        private const val LIMIT_ITEMS = "50"
    }

}
