package com.kwon.mylgproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kwon.mylgproject.R
import com.kwon.mylgproject.contains.ScreenDefine
import com.kwon.mylgproject.viewmodel.ScreenViewModel
import com.kwon.mylgproject.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScreenViewModel.getInstance(this).screenStatus.observe(this, { fragment_name ->
            changeFragment(fragment_name)
        })

//        RoomDataBase.getInstance(this)?.scheduleService()?.let {
//            it.reset()
//
//            var count = 0
//            for(k in 0..10000) {
//                Log.d("TEST", "생성_$k")
//                it.create(ScheduleViewModel(this)?.getScheduleRecord(
//                    type = "normal_$count",
//                    contents = "contents_$count",
//                    title = "title_$count",
//                    memo = "memo_$count",
//                    from_time = count.toLong(),
//                    to_time = (count + 1).toLong()
//                ))
//                count++
//            }
//            count = 0
//        }
    }

    private fun changeFragment(fragment_type:String) {
        supportFragmentManager.beginTransaction().let { ft ->
            fragment_type.let { ty ->
                when (ty) {
                    ScreenDefine.HOME_FRAGMENT -> HomeFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.USER_INFO_FRAGMENT -> UserInfoFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.SCHEDULE_FRAGMENT -> ScheduleFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.STUDY_FRAGMENT -> StudyFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.ACTIVITY_FRAGMENT -> ActivityFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    else -> HomeFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                }
            }
        }
    }
}