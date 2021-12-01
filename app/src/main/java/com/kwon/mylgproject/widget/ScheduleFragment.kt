package com.kwon.mylgproject.widget

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kwon.mylgproject.R

class ScheduleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST", "- 스케쥴 프레그먼트 -")

//        val adapter = ScheduleAdapter()
//        sc_recycler.adapter = adapter
//        ScheduleViewModel(requireContext())?.let { vm ->
//            vm.getScheduleByPage()?.let { ob ->
//                lifecycleScope.launch {
//                    ob?.collectLatest {
//                        adapter.submitData(it)
//                    }
//                }
//            }
//        }
    }
}
