package com.insider0piyush.sms_rc1.shared

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.AdminLogin
import com.insider0piyush.sms_rc1.databinding.LoginUserBinding
import com.insider0piyush.sms_rc1.faculty.FacultyHome
import com.insider0piyush.sms_rc1.home.MainHomeUser
import com.insider0piyush.sms_rc1.student.StudentHome

class LoginUser : AppCompatActivity() {
    private lateinit var binding: LoginUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            if(isStudent()){
                startActivity(Intent(this,StudentHome::class.java).setAction(Intent.ACTION_VIEW))
            }else if(isFaculty()){
                startActivity(Intent(this,FacultyHome::class.java).setAction(Intent.ACTION_VIEW))
            }
            startActivity(Intent(this,MainHomeUser::class.java).setAction(Intent.ACTION_VIEW))
        }
        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.admin ->
                {
                    startActivity(Intent(this,AdminLogin::class.java).setAction(Intent.ACTION_VIEW))
                }
            }
            false
        }
    }
    private fun isStudent() : Boolean {
        return true
    }

    private fun isFaculty() : Boolean{
        return true
    }
}