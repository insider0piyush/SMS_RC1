package com.insider0piyush.sms_rc1.admin.home.util.manage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.insider0piyush.sms_rc1.admin.home.util.SettingsActivity
import com.insider0piyush.sms_rc1.databinding.AdminManageBinding

class AdminManage : Fragment() {
    lateinit var binding : AdminManageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdminManageBinding.inflate(inflater,container,false)
        binding.CardSetting.setOnClickListener{
            startActivity(Intent(context,SettingsActivity::class.java).setAction(Intent.ACTION_VIEW))
        }
        return binding.root
    }
}