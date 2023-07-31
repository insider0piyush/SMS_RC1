package com.insider0piyush.sms_rc1.admin.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.admin.util.sharedpref.AdminLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.AdminHomeBinding

class AdminHome : AppCompatActivity() {
    private lateinit var binding: AdminHomeBinding
    lateinit var sharedPref: AdminLoginSharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= AdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = AdminLoginSharedPref(this)

        sharedPref.checkLogin()

        binding.btnSignOut.setOnClickListener {
            sharedPref.logOut()
        }
    }
}