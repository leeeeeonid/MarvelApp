package com.lealpy.marvelapp.data.models

data class CharactersData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharactersResult>,
)
