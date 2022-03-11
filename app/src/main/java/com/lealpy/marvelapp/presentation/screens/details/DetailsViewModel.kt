package com.lealpy.marvelapp.presentation.screens.details

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.use_cases.GetCharacterByIdUseCase
import com.lealpy.marvelapp.presentation.screens.BaseViewModel
import com.lealpy.marvelapp.presentation.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : BaseViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun onArgsReceived(characterId: Int) {
        getCharacterById(characterId)
    }

    private fun getCharacterById(characterId: Int) {
        _progressBarVisibility.value = View.VISIBLE
        disposable.add(
            getCharacterByIdUseCase(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { character ->
                        _character.value = character
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(Const.APP_LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

}
