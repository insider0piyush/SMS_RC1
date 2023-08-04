package com.insider0piyush.sms_rc1.admin.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.user.FacultyAdd
import com.insider0piyush.sms_rc1.admin.home.user.StudentAdd
import com.insider0piyush.sms_rc1.admin.util.sharedpref.AdminLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.AdminHomeBinding

class AdminHome : AppCompatActivity() {
    private lateinit var binding: AdminHomeBinding
    private lateinit var sharedPref: AdminLoginSharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= AdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = AdminLoginSharedPref(this)
        sharedPref.checkLogin()

        binding.FloatingActionBtn.setOnClickListener {
            selectUser()
        }
        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.UserAccountProfile -> {
                    showToast("work in progress ⌛")
                }
                R.id.Help -> {
                    showToast("Help")
                }
                R.id.Feedback -> {
                    showToast(("Feedback"))
                }
            }
            true
        }
    }

    private fun selectUser(){
        val user = arrayOf(resources.getString(R.string.student),   resources.getString(R.string.faculty))
        val builder = MaterialAlertDialogBuilder(this)
        with(builder){
            builder.setItems(user){
                    _, it -> when(it){
                    0 -> {
                        startActivity(Intent(context,StudentAdd::class.java).setAction(Intent.ACTION_VIEW))
                    }
                    1 -> {
                        startActivity(Intent(context,FacultyAdd::class.java).setAction(Intent.ACTION_VIEW))

                    }
                }
            }
        }
        builder.create().show()
    }

    private fun showToast(str:String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}