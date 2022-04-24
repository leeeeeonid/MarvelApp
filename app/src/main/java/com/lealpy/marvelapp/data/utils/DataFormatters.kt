package com.lealpy.marvelapp.data.utils

import com.lealpy.marvelapp.data.database.CharacterEntity
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

fun List<Character>.toCharacterEntities(): List<CharacterEntity> {
    return this.map { character ->
        CharacterEntity(
            id = character.id,
            name = character.name,
            description = character.description,
            imageURL = character.imageURL,
            modified = character.modified
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

fun List<CharacterEntity>.entityToCharacters(): List<Character> {
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
