package com.lealpy.marvelapp.presentation.screens.characters

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.marvelapp.domain.models.SortBy
import com.lealpy.marvelapp.domain.use_cases.GetCharactersUseCase
import com.lealpy.marvelapp.presentation.models.CharacterUi
import com.lealpy.marvelapp.presentation.screens.BaseViewModel
import com.lealpy.marvelapp.presentation.utils.Const.APP_LOG_TAG
import com.lealpy.marvelapp.presentation.utils.toCharactersUi
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel() {

    private val _charactersUi = MutableLiveData<List<CharacterUi>>()
    val charactersUi: LiveData<List<CharacterUi>> = _charactersUi

    init {
        getCharactersUi()
    }

    fun onSwipedRefresh() {
        getCharactersUi()
    }

    fun onSortByClicked(sortBy: SortBy) {
        val charactersUi = _charactersUi.value
        if (charactersUi != null) {
            when (sortBy) {
                SortBy.BY_ALPHABET -> {
                    _charactersUi.value = charactersUi.sortedBy { character ->
                        character.name
                    }
                }
                SortBy.BY_DATE -> {
                    _charactersUi.value = charactersUi.sortedBy { character ->
                        character.modified
                    }
                }
                SortBy.BY_ALPHABET_DESCENDING -> {
                    _charactersUi.value = charactersUi.sortedByDescending { character ->
                        character.name
                    }
                }
                SortBy.BY_DATE_DESCENDING -> {
                    _charactersUi.value = charactersUi.sortedByDescending { character ->
                        character.modified
                    }
                }
            }
        } else {
            getCharactersUi()
        }
    }

    private fun getCharactersUi() {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getCharactersUseCase()
                .map { characters ->
                    characters.toCharactersUi()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { charactersUi ->
                        _charactersUi.value = charactersUi
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(APP_LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

}
