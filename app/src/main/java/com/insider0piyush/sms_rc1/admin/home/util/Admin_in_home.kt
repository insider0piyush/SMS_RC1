package com.insider0piyush.sms_rc1.admin.home.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.insider0piyush.sms_rc1.databinding.AdminInHomeBinding

class Admin_in_home : Fragment() {
    lateinit var binding: AdminInHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdminInHomeBinding.inflate(inflater,container,false)




        return binding.root
    }
}