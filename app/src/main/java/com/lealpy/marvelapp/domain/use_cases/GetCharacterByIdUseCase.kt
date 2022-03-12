package com.lealpy.marvelapp.domain.use_cases

import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {

    operator fun invoke(characterId: Int): Single<Character> {
        return charactersRepository.getCharacterById(characterId)
    }

}
