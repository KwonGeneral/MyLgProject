package com.kwon.mylgproject.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kwon.mylgproject.data.ScheduleRecord
import com.kwon.mylgproject.db.RoomDataBase
import com.kwon.mylgproject.utils.PagedRepository
import kotlinx.coroutines.flow.Flow

class ScheduleViewModel(var context: Context) : ViewModel() {
    var scheduleDataList = MutableLiveData<List<ScheduleRecord>>()
    var schedulePageList = MutableLiveData<Flow<PagingData<ScheduleRecord>>>()

    var schedule = MutableLiveData<PagingData<ScheduleRecord>>()


    fun getScheduleRecord(
        type: String? = null,
        contents: String? = null,
        title: String? = null,
        memo: String? = null,
        from_time: Long? = null,
        to_time: Long? = null): ScheduleRecord {

        return ScheduleRecord(
            id = null,
            type = type!!,
            contents = contents!!,
            title = title!!,
            memo = memo!!,
            from_time = from_time!!,
            to_time = to_time!!
        )
    }

    fun getScheduleReadAll() {
        RoomDataBase.getInstance(context)?.scheduleService()?.readAll()?.let {
            scheduleDataList.postValue(it)
        }
    }

    fun getScheduleByPage() :Flow<PagingData<ScheduleRecord>> {
        val schedules: Flow<PagingData<ScheduleRecord>> = PagedRepository(RoomDataBase.getInstance(context)!!).getSchedules().cachedIn(viewModelScope)
        return schedules
    }

    fun getScheduleByTime(from_time: Long, to_time: Long) {
        RoomDataBase.getInstance(context)?.scheduleService()?.readByTime(from_time, to_time)?.let {
            scheduleDataList.postValue(it)
        }
    }

    fun getScheduleSearchByTitle(title: String) {
        RoomDataBase.getInstance(context)?.scheduleService()?.searchByTitle(title)?.let {
            scheduleDataList.postValue(it)
        }
    }

    fun setScheduleData(scheduleRecord: ScheduleRecord): Boolean {
        RoomDataBase.getInstance(context)?.scheduleService()?.update(scheduleRecord)?.let {
            return true
        }
        return false
    }

    fun deleteScheduleData(id: Long): Boolean {
        RoomDataBase.getInstance(context)?.scheduleService()?.delete(id)?.let {
            return true
        }
        return false
    }

    fun createScheduleData(scheduleRecord: ScheduleRecord): Boolean {
        RoomDataBase.getInstance(context)?.scheduleService()?.create(scheduleRecord)?.let {
            return true
        }
        return false
    }
}