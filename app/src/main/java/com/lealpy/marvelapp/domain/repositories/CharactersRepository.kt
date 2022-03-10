package com.lealpy.marvelapp.domain.repositories

import com.lealpy.marvelapp.domain.models.Character
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun getCharacters(): Single<List<Character>>

}
