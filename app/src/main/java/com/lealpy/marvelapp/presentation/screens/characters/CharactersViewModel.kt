package com.lealpy.marvelapp.presentation.screens.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.models.SortBy
import com.lealpy.marvelapp.domain.use_cases.GetCharactersUseCase
import com.lealpy.marvelapp.presentation.screens.BaseViewModel
import com.lealpy.marvelapp.presentation.utils.Const.APP_LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    init {
        getCharacters()
    }

    fun onSwipedRefresh() {
        getCharacters()
    }

    fun onSortByClicked(sortBy: SortBy) {
        val characters = _characters.value
        if (characters != null) {
            when (sortBy) {
                SortBy.BY_ALPHABET -> {
                    _characters.value = characters.sortedBy { character ->
                        character.name
                    }
                }
                SortBy.BY_DATE -> {
                    _characters.value = characters.sortedBy { character ->
                        character.modified
                    }
                }
                SortBy.BY_ALPHABET_DESCENDING -> {
                    _characters.value = characters.sortedByDescending { character ->
                        character.name
                    }
                }
                SortBy.BY_DATE_DESCENDING -> {
                    _characters.value = characters.sortedByDescending { character ->
                        character.modified
                    }
                }
            }
        } else {
            getCharacters()
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                showProgress()

                val characters = withContext(Dispatchers.IO) {
                    getCharactersUseCase()
                }

                _characters.value = characters
                hideProgress()
            } catch (e: Exception) {
                Log.e(APP_LOG_TAG, e.message.toString())
                hideProgress()
            }
        }
    }

}
