package com.kwon.mylgproject.api

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kwon.mylgproject.data.ActivityRecord

import com.kwon.mylgproject.data.ScheduleRecord
import com.kwon.mylgproject.data.StudyRecord
import com.kwon.mylgproject.data.UserData

@Dao
interface UserService {
    @Query("SELECT * FROM tb_user")
    fun read(): UserData
    @Update
    fun update(vararg record: UserData)
    @Query("DELETE FROM tb_user WHERE id = :id")
    fun delete(id: Long)
    @Insert
    fun create(vararg record: UserData)
}

@Dao
interface ScheduleService {
    @Query("SELECT * FROM schedule_record")
    fun getTestPage(): PagingSource<Int, ScheduleRecord>

    @Query("SELECT * FROM schedule_record")
    fun getTestPage2(): List<ScheduleRecord>

    @Query("SELECT * FROM schedule_record WHERE title = :title")
    fun getTestPage3(title: String): PagingSource<Int, ScheduleRecord>

    @Query("SELECT * FROM schedule_record")
    fun readAll(): List<ScheduleRecord>
    @Query("select * from schedule_record order by id DESC LIMIT :loadSize OFFSET :index * :loadSize")
    fun readByPage(index : Int, loadSize : Int) : List<ScheduleRecord>
    @Query("SELECT * FROM schedule_record WHERE from_time = :from_time AND to_time = :to_time")
    fun readByTime(from_time:Long, to_time:Long): List<ScheduleRecord>
    @Query("SELECT * FROM schedule_record WHERE title = :title")
    fun searchByTitle(title: String): List<ScheduleRecord>
    @Update
    fun update(vararg record: ScheduleRecord)
    @Query("DELETE FROM schedule_record WHERE id = :id")
    fun delete(id: Long)
    @Insert
    fun create(vararg record: ScheduleRecord)

    @Query("DELETE FROM schedule_record")
    fun reset()
}


@Dao
interface StudyService {
    @Query("SELECT * FROM study_record")
    fun readAll(): List<StudyRecord>
    @Query("select * from study_record order by id DESC LIMIT :loadSize OFFSET :index * :loadSize")
    fun readByPage(index : Int, loadSize : Int) : List<StudyRecord>
    @Query("SELECT * FROM study_record WHERE title = :title")
    fun searchByTitle(title: String): List<StudyRecord>
    @Update
    fun update(vararg record: StudyRecord)
    @Query("DELETE FROM schedule_record WHERE id = :id")
    fun delete(id: Long)
    @Insert
    fun create(vararg record: StudyRecord)
}

@Dao
interface ActivityService {
    @Query("SELECT * FROM activity_record")
    fun readAll(): List<ActivityRecord>
    @Query("select * from activity_record order by id DESC LIMIT :loadSize OFFSET :index * :loadSize")
    fun readByPage(index : Int, loadSize : Int) : List<ActivityRecord>
    @Query("SELECT * FROM activity_record WHERE title = :title")
    fun searchByTitle(title: String): List<ActivityRecord>
    @Update
    fun update(vararg record: ActivityRecord)
    @Query("DELETE FROM activity_record WHERE id = :id")
    fun delete(id: Long)
    @Insert
    fun create(vararg record: ActivityRecord)
}