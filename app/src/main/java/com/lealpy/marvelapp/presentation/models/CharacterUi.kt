package com.lealpy.marvelapp.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUi (
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: String,
    val modified: String,
): Parcelable
