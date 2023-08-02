package com.insider0piyush.sms_rc1.admin.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        binding.FloatingActionBtn.setOnClickListener {
            selectUser()
        }
    }

    private fun selectUser(){
        val user = arrayOf(resources.getString(R.string.student),   resources.getString(R.string.faculty))
        val builder = MaterialAlertDialogBuilder(this)
        with(builder){
            builder.setItems(user){
                dialog, it -> when(it){
                    0 -> {

                    }
                    1 -> {

                    }
                }
            }
        }
        builder.create().show()
    }

    private fun replaceFrag(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout,fragment)
        fragmentTransaction.commit()
    }

    private fun showToast(str:String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}