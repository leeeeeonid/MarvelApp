package com.lealpy.marvelapp.presentation.utils

import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.presentation.models.CharacterUi

fun List<Character>.toCharactersUi(): List<CharacterUi> {
    return this.map { character ->
        CharacterUi(
            id = character.id,
            name = character.name,
            description = character.description,
            imageURL = character.imageURL,
            modified = character.modified
        )
    }
}
