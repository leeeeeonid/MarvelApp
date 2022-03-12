package com.lealpy.marvelapp.data.models

data class CharactersResult(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<CharactersUrl>,
    val thumbnail: CharactersThumbnail,
    val comics: Comics,
    val stories: Stories,
    val events: Events,
    val series: Series,
)
