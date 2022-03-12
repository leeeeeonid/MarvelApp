package com.lealpy.marvelapp.data.models

data class Events(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<EventsItem>,
)
