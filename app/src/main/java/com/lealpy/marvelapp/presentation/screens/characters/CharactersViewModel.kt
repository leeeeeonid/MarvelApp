package com.lealpy.marvelapp.presentation.screens.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.models.SortBy
import com.lealpy.marvelapp.domain.use_cases.GetCharactersUseCase
import com.lealpy.marvelapp.presentation.screens.BaseViewModel
import com.lealpy.marvelapp.presentation.utils.Const.APP_LOG_TAG
import com.lealpy.marvelapp.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val navigateToFilterEvent = SingleLiveEvent<SortBy>()
    fun navigateToFilter() = navigateToFilterEvent

    private var sortBy = SortBy.BY_ALPHABET

    init {
        getCharacters()
    }

    fun onSwipedRefresh() {
        getCharacters()
    }

    fun onSortByClicked(sortBy: SortBy) {
        this.sortBy = sortBy
        val characters = _characters.value

        if (characters != null) {
            _characters.value = getSortedCharacters(characters, sortBy)
        } else {
            getCharacters()
        }
    }

    fun onCharactersFilterBtnClicked() {
        navigateToFilterEvent.value = sortBy
    }

    private fun getCharacters() {
        showProgress()

        disposable.add(
            getCharactersUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { characters ->
                        _characters.value = getSortedCharacters(characters, sortBy)
                        hideProgress()
                    },
                    { error ->
                        Log.e(APP_LOG_TAG, error.message.toString())
                        hideProgress()
                    }
                )
        )
    }

    private fun getSortedCharacters(characters: List<Character>, sortBy: SortBy): List<Character> {
        return when (sortBy) {
            SortBy.BY_ALPHABET -> {
                characters.sortedBy { character ->
                    character.name
                }
            }
            SortBy.BY_DATE -> {
                characters.sortedBy { character ->
                    character.modified
                }
            }
            SortBy.BY_ALPHABET_DESCENDING -> {
                characters.sortedByDescending { character ->
                    character.name
                }
            }
            SortBy.BY_DATE_DESCENDING -> {
                characters.sortedByDescending { character ->
                    character.modified
                }
            }
        }
    }

}
