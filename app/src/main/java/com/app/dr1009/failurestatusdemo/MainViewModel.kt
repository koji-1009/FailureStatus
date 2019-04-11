package com.app.dr1009.failurestatusdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.dr1009.failurestatus.FailureStatus

class MainViewModel : ViewModel() {

    private val errorList = ExceptionList.values().map { it.throwable }

    val exceptionList = errorList.map { it.toString() }
    val exceptionSelect = MutableLiveData<Int>()
    val failureStatus = MutableLiveData<FailureStatus>()

    fun load() {
        failureStatus.postValue(FailureStatus(errorList[exceptionSelect.value ?: 0]))
    }
}
