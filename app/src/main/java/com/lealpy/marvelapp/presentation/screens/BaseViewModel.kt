package com.lealpy.marvelapp.presentation.screens

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    protected fun showProgress() {
        _progressBarVisibility.value = View.VISIBLE
    }

    protected fun hideProgress() {
        _progressBarVisibility.value = View.GONE
    }

}
