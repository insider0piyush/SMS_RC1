package com.insider0piyush.sms_rc1.admin.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.util.Admin_in_home
import com.insider0piyush.sms_rc1.admin.home.util.SettingsActivity
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
        replaceFrag(Admin_in_home())
        sharedPref.checkLogin()

        binding.BottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home -> {
                    replaceFrag(Admin_in_home())
                }
                R.id.Setting -> {
                    startActivity(Intent(this,SettingsActivity::class.java).setAction(Intent.ACTION_VIEW))
                }
            }
            true
        }
    }

    private fun replaceFrag(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout,fragment)
        fragmentTransaction.commit()
    }
}