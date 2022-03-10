package com.lealpy.marvelapp.data.models

data class Series(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<SeriesItem>,
)
