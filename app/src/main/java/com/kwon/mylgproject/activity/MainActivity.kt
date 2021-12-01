package com.kwon.mylgproject.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kwon.mylgproject.R
import com.kwon.mylgproject.contains.ScreenDefine
import com.kwon.mylgproject.viewmodel.ScreenViewModel
import com.kwon.mylgproject.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScreenViewModel.getInstance(this).screenStatus.observe(this, { fragment_name ->
            changeFragment(fragment_name)
        })

        main_nav.onItemClick = { action ->
            Log.d("TEST", "action : $action")
        }
    }

    private fun changeFragment(fragment_type:String) {
        supportFragmentManager.beginTransaction().let { ft ->
            fragment_type.let { ty ->
                when (ty) {
                    ScreenDefine.HOME_FRAGMENT -> HomeFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.LIFE_RECORD_FRAGMENT -> UserInfoFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.RECORD_FRAGMENT -> ScheduleFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.CALENDAR_FRAGMENT -> StudyFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    ScreenDefine.EXAM_FRAGMENT -> ActivityFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    else -> HomeFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                }
            }
        }
    }
}