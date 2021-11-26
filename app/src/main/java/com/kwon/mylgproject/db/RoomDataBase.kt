package com.kwon.mylgproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kwon.mylgproject.api.ActivityService
import com.kwon.mylgproject.api.ScheduleService
import com.kwon.mylgproject.api.StudyService
import com.kwon.mylgproject.api.UserService
import com.kwon.mylgproject.data.ActivityRecord
import com.kwon.mylgproject.data.ScheduleRecord
import com.kwon.mylgproject.data.StudyRecord
import com.kwon.mylgproject.data.UserData

@Database(entities = [UserData::class, ScheduleRecord::class, StudyRecord::class, ActivityRecord::class], version = 1, exportSchema = false)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun userService(): UserService
    abstract fun scheduleService(): ScheduleService
    abstract fun studyService(): StudyService
    abstract fun activityService(): ActivityService

    companion object {
        var instance: RoomDataBase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDataBase? {
            instance?.let {
                return it
            }
            instance = Room.databaseBuilder(
                context.applicationContext,
                RoomDataBase::class.java,
                "room_db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

            return instance
        }
    }
}