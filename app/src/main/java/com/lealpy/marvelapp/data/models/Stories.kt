package com.lealpy.marvelapp.data.models

data class Stories(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<StoriesItem>,
)
