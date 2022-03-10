package com.lealpy.marvelapp.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.marvelapp.presentation.models.CharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(

) : ViewModel() {

    private val _characterUi = MutableLiveData<CharacterUi>()
    val characterUi: LiveData<CharacterUi> = _characterUi

    fun onArgsReceived(characterUi: CharacterUi) {
        _characterUi.value = characterUi
    }
}