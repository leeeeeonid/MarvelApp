package com.lealpy.marvelapp.data.repositories

import com.lealpy.marvelapp.data.api.CharactersApi
import com.lealpy.marvelapp.data.database.CharactersDao
import com.lealpy.marvelapp.data.utils.entityToCharacters
import com.lealpy.marvelapp.data.utils.toCharacter
import com.lealpy.marvelapp.data.utils.toCharacterEntities
import com.lealpy.marvelapp.data.utils.toCharacters
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
) : CharactersRepository {

    override fun getCharacters(): Single<List<Character>> {
        return charactersApi.getCharacters(limit = LIMIT_ITEMS).map { charactersResponse ->
            charactersResponse.data.results.toCharacters()
        }
            .doOnSuccess { characters ->
                charactersDao.insertCharacterEntities(
                    characters.toCharacterEntities()
                ).blockingSubscribe()
            }
            .onErrorResumeNext {
                charactersDao.getCharacterEntities().map { characterEntities ->
                    characterEntities.entityToCharacters()
                }
            }
    }

    override fun getCharacterById(characterId: Int): Single<Character> {
        return charactersDao.getCharacterEntityById(characterId).map { characterEntity ->
            characterEntity.toCharacter()
        }
    }

    companion object {
        private const val LIMIT_ITEMS = "50"
    }

}
