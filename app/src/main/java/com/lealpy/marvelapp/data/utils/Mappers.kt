package com.lealpy.marvelapp.data.utils

import com.lealpy.marvelapp.data.models.CharactersResult
import com.lealpy.marvelapp.domain.models.Character

fun CharactersResult.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        description = this.description,
        imageURL = "${this.thumbnail.path}.${this.thumbnail.extension}",
        modified = this.modified
    )
}

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
