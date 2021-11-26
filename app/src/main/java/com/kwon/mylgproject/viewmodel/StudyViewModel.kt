package com.kwon.mylgproject.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kwon.mylgproject.data.ScheduleRecord
import com.kwon.mylgproject.data.StudyRecord
import com.kwon.mylgproject.db.RoomDataBase

class StudyViewModel(var context: Context) {
    var studyDataList = MutableLiveData<List<StudyRecord>>()

    fun getStudyRecord(
        type: String? = null,
        contents: String? = null,
        title: String? = null,
        memo: String? = null,
        time: Long? = null): StudyRecord {

        return StudyRecord(
            id = null,
            type = type!!,
            contents = contents!!,
            title = title!!,
            memo = memo!!,
            time = time!!
        )
    }

    fun getStudyReadAll() {
        RoomDataBase.getInstance(context)?.studyService()?.readAll()?.let {
            studyDataList.postValue(it)
        }
    }

    fun getStudyByPage(index: Int, loadSize: Int) {
        RoomDataBase.getInstance(context)?.studyService()?.readByPage(index, loadSize)?.let {
            studyDataList.postValue(it)
        }
    }

    fun getStudySearchByTitle(title: String) {
        RoomDataBase.getInstance(context)?.studyService()?.searchByTitle(title)?.let {
            studyDataList.postValue(it)
        }
    }

    fun setStudyData(studyRecord: StudyRecord): Boolean {
        RoomDataBase.getInstance(context)?.studyService()?.update(studyRecord)?.let {
            return true
        }
        return false
    }

    fun deleteStudyData(id: Long): Boolean {
        RoomDataBase.getInstance(context)?.studyService()?.delete(id)?.let {
            return true
        }
        return false
    }

    fun createStudyData(studyRecord: StudyRecord): Boolean {
        RoomDataBase.getInstance(context)?.studyService()?.create(studyRecord)?.let {
            return true
        }
        return false
    }
}