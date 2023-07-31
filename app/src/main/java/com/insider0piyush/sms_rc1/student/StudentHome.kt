package com.insider0piyush.sms_rc1.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.databinding.StudentHomeBinding

class StudentHome : AppCompatActivity() {
    private lateinit var binding : StudentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StudentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}