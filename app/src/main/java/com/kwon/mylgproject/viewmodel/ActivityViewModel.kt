package com.kwon.mylgproject.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kwon.mylgproject.data.ActivityRecord
import com.kwon.mylgproject.db.RoomDataBase

class ActivityViewModel(var context: Context) {
    var activityDataList = MutableLiveData<List<ActivityRecord>>()

    fun getActivityRecord(
        type: String? = null,
        contents: String? = null,
        title: String? = null,
        memo: String? = null,
        time: Long? = null): ActivityRecord {

        return ActivityRecord(
            id = null,
            type = type!!,
            contents = contents!!,
            title = title!!,
            memo = memo!!,
            time = time!!
        )
    }

    fun getActivityReadAll() {
        RoomDataBase.getInstance(context)?.activityService()?.readAll()?.let {
            activityDataList.postValue(it)
        }
    }

    fun getActivityByPage(index: Int, loadSize: Int) {
        RoomDataBase.getInstance(context)?.activityService()?.readByPage(index, loadSize)?.let {
            activityDataList.postValue(it)
        }
    }

    fun getActivitySearchByTitle(title: String) {
        RoomDataBase.getInstance(context)?.activityService()?.searchByTitle(title)?.let {
            activityDataList.postValue(it)
        }
    }

    fun setActivityData(studyRecord: ActivityRecord): Boolean {
        RoomDataBase.getInstance(context)?.activityService()?.update(studyRecord)?.let {
            return true
        }
        return false
    }

    fun deleteActivityData(id: Long): Boolean {
        RoomDataBase.getInstance(context)?.activityService()?.delete(id)?.let {
            return true
        }
        return false
    }

    fun createActivityData(studyRecord: ActivityRecord): Boolean {
        RoomDataBase.getInstance(context)?.activityService()?.create(studyRecord)?.let {
            return true
        }
        return false
    }
}