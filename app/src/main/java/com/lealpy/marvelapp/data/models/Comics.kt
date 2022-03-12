package com.lealpy.marvelapp.data.models

data class Comics(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<ComicsItem>,
)
