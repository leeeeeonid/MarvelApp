package com.lealpy.marvelapp.data.repositories

import com.lealpy.marvelapp.data.api.CharactersApi
import com.lealpy.marvelapp.data.utils.toCharacters
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
) : CharactersRepository {

    override fun getCharacters(): Single<List<Character>> {
        return charactersApi.getCharacters().map { characterResponse ->
            characterResponse.data.results.toCharacters()
        }
    }

}
