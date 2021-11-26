package com.kwon.mylgproject.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kwon.mylgproject.data.UserData
import com.kwon.mylgproject.db.RoomDataBase

class UserViewModel(var context: Context) {
    var userData = MutableLiveData<UserData>()

    fun getUserData(user_name: String? = null, nickname: String? = null, user_profile: String? = null): UserData {
        return UserData(
            id = null,
            user_name = user_name!!,
            nickname = nickname!!,
            user_profile = user_profile!!
        )
    }

    fun getUserInfo() {
        RoomDataBase.getInstance(context)?.userService()?.read()?.let {
            userData.postValue(it)
        }
    }

    fun setUserInfo(user_data: UserData): Boolean {
        RoomDataBase.getInstance(context)?.userService()?.update(user_data)?.let {
            return true
        }
        return false
    }

    fun deleteUserInfo(id: Long): Boolean {
        RoomDataBase.getInstance(context)?.userService()?.delete(id)?.let {
            return true
        }
        return false
    }

    fun createUserInfo(user_data: UserData): Boolean {
        RoomDataBase.getInstance(context)?.userService()?.create(user_data)?.let {
            return true
        }
        return false
    }
}