package com.lealpy.marvelapp.data.repositories

import com.lealpy.marvelapp.data.api.CharactersApi
import com.lealpy.marvelapp.data.database.CharactersDao
import com.lealpy.marvelapp.data.utils.toCharacter
import com.lealpy.marvelapp.data.utils.toCharacterEntities
import com.lealpy.marvelapp.data.utils.toCharacters
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
) : CharactersRepository {

    override fun getCharacters(): Single<List<Character>> {
        return charactersApi.getCharacters(limit = LIMIT_ITEMS).map { characterResponse ->
            characterResponse.data.results.toCharacters()
        }
            .doOnSuccess { characters ->
                insertCharactersToDb(characters).blockingSubscribe()
            }
    }

    override fun getCharacterById(characterId: Int): Single<Character> {
        return charactersDao.getCharacterEntityById(characterId).map { characterEntity ->
            characterEntity.toCharacter()
        }
    }

    private fun insertCharactersToDb(characters: List<Character>): Completable {
        return charactersDao.insertCharacterEntities(characters.toCharacterEntities())
    }

    companion object {
        private const val LIMIT_ITEMS = "50"
    }

}
