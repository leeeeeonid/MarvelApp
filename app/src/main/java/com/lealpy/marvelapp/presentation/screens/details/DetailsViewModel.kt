package com.lealpy.marvelapp.presentation.screens.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.use_cases.GetCharacterByIdUseCase
import com.lealpy.marvelapp.presentation.screens.BaseViewModel
import com.lealpy.marvelapp.presentation.utils.Const.APP_LOG_TAG
import com.lealpy.marvelapp.presentation.utils.Const.CHARACTER_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : BaseViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    init {
        savedStateHandle.get<Int>(CHARACTER_ID_KEY)?.let { characterId ->
            getCharacterById(characterId)
        }
    }

    private fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            try {
                showProgress()

                val character = withContext(Dispatchers.IO) {
                    getCharacterByIdUseCase(characterId)
                }

                _character.value = character
                hideProgress()
            } catch (e: Exception) {
                Log.e(APP_LOG_TAG, e.message.toString())
                hideProgress()
            }
        }
    }

}
