package com.kwon.mylgproject.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kwon.mylgproject.contains.ScreenDefine

class ScreenViewModel(val context: Context) {
    var screenStatus = MutableLiveData<String>()

    init {
        screenStatus.postValue(ScreenDefine.SCHEDULE_FRAGMENT)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: ScreenViewModel? = null
        fun getInstance(context: Context): ScreenViewModel {
            instance?.let {
                return it
            }
            instance = ScreenViewModel(context)
            return instance!!
        }
    }
}
