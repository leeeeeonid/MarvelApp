package com.lealpy.marvelapp.data.models

data class CharactersResponse (
    val code: Int,
    val status: String,
    val data: CharactersData,
    val etag: String,
    val copyright: String,
    val attributionHTML: String,
    val attributionText: String,
)
