package com.lealpy.marvelapp.data.utils

import com.lealpy.marvelapp.data.models.CharactersResult
import com.lealpy.marvelapp.domain.models.Character

fun List<CharactersResult>.toCharacters(): List<Character> {
    return this.map { charactersResult ->
        Character(
            id = charactersResult.id,
            name = charactersResult.name,
            description = charactersResult.description,
            imageURL = "${charactersResult.thumbnail.path}.${charactersResult.thumbnail.extension}",
            modified = charactersResult.modified
        )
    }
}
