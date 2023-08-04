package com.insider0piyush.sms_rc1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.admin.AdminLogin
import com.insider0piyush.sms_rc1.admin.home.user.login.Faculty
import com.insider0piyush.sms_rc1.admin.home.user.login.Student
import com.insider0piyush.sms_rc1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStudent.setOnClickListener {
            startActivity(Intent(this,Student::class.java).setAction(Intent.ACTION_VIEW))
        }
        binding.btnFaculty.setOnClickListener {
            startActivity(Intent(this,Faculty::class.java).setAction(Intent.ACTION_VIEW))
        }
        binding.btnSkip.setOnClickListener {
            startActivity(Intent(this,AdminLogin::class.java).setAction(Intent.ACTION_VIEW))
        }
    }
}