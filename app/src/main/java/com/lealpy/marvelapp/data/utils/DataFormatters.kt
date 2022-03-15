package com.lealpy.marvelapp.data.utils

import com.lealpy.marvelapp.data.database.CharacterEntity
import com.lealpy.marvelapp.data.models.CharactersResult
import com.lealpy.marvelapp.domain.models.Character

fun List<CharactersResult>.toCharacterEntities(): List<CharacterEntity> {
    return this.map { charactersResult ->
        CharacterEntity(
            id = charactersResult.id,
            name = charactersResult.name,
            description = charactersResult.description,
            imageURL = "${charactersResult.thumbnail.path}.${charactersResult.thumbnail.extension}",
            modified = charactersResult.modified
        )
    }
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        description = this.description,
        imageURL = this.imageURL,
        modified = this.modified
    )
}

fun List<CharacterEntity>.toCharacters(): List<Character> {
    return this.map { characterEntity ->
        Character(
            id = characterEntity.id,
            name = characterEntity.name,
            description = characterEntity.description,
            imageURL = characterEntity.imageURL,
            modified = characterEntity.modified
        )
    }
}
