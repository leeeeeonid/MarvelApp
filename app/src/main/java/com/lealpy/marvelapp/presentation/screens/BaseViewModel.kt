package com.lealpy.marvelapp.presentation.screens

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    protected fun showProgress() {
        _progressBarVisibility.value = View.VISIBLE
    }

    protected fun hideProgress() {
        _progressBarVisibility.value = View.GONE
    }

}
