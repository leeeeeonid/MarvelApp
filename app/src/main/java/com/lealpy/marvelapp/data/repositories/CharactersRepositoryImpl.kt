package com.lealpy.marvelapp.data.repositories

import android.util.Log
import com.lealpy.marvelapp.data.api.CharactersApi
import com.lealpy.marvelapp.data.database.CharactersDao
import com.lealpy.marvelapp.data.utils.toCharacter
import com.lealpy.marvelapp.data.utils.toCharacterEntities
import com.lealpy.marvelapp.data.utils.toCharacters
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import com.lealpy.marvelapp.presentation.utils.Const
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        try {
            val characterEntities = charactersApi.getCharacters(limit = LIMIT_ITEMS)
                .data
                .results
                .toCharacterEntities()
            charactersDao.insertCharacterEntities(characterEntities)
        } catch (e: Exception) {
            Log.e(Const.APP_LOG_TAG, e.message.toString())
        }

        return charactersDao.getCharacterEntities().toCharacters()
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        return charactersDao.getCharacterEntityById(characterId).toCharacter()
    }

    companion object {
        private const val LIMIT_ITEMS = "50"
    }

}
