package com.kwon.mylgproject.widget

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kwon.mylgproject.R
import com.kwon.mylgproject.viewmodel.UserViewModel

class UserInfoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST", "- 유저정보 프레그먼트 -")

        UserViewModel(requireContext())?.let { user ->
            user.getUserInfo()
            user.userData.observe(viewLifecycleOwner, {
            })
        }

    }
}