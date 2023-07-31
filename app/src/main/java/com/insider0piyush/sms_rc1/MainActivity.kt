package com.insider0piyush.sms_rc1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.insider0piyush.sms_rc1.shared.LoginUser
import com.insider0piyush.sms_rc1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnEnter.setOnClickListener {
            startActivity(Intent(this, LoginUser::class.java).setAction(Intent.ACTION_VIEW))
        }
    }
}