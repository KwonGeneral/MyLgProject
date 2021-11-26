package com.kwon.mylgproject.widget

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kwon.it_word.adapter.ScheduleAdapter
import com.kwon.mylgproject.R
import com.kwon.mylgproject.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST", "스케쥴 프레그먼트 입니다!")

        val adapter = ScheduleAdapter()
        sc_recycler.adapter = adapter

        ScheduleViewModel(requireContext())?.let { vm ->
            vm.getScheduleByPage()?.let { ob ->
                lifecycleScope.launch {
                    ob?.collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }

    }
}
