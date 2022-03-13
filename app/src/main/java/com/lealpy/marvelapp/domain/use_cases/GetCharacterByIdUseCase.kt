package com.lealpy.marvelapp.domain.use_cases

import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {

    suspend operator fun invoke(characterId: Int): Character {
        return charactersRepository.getCharacterById(characterId)
    }

}
