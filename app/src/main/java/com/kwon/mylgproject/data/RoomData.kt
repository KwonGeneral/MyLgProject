package com.kwon.mylgproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val user_name: String = "",
    val nickname: String = "",
    val user_profile: String = "",
    val created_at: String = "",
)
@Entity(tableName = "schedule_record")
data class ScheduleRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val type:String = "",
    val contents:String = "",
    val title:String = "",
    val memo: String = "",
    val from_time:Long = 0,
    val to_time:Long = 0,
)
@Entity(tableName = "study_record")
data class StudyRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val type:String = "",
    val contents:String ="",
    val title:String = "",
    val memo:String = "",
    val time:Long = 0,
)
@Entity(tableName = "activity_record")
data class ActivityRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val time:Long = 0,
    val type:String = "",
    val contents:String ="",
    val title:String = "",
    val memo:String = "",
)