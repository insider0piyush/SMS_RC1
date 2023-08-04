package com.insider0piyush.sms_rc1.student

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.util.sharedpref.StudentLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.StudentHomeBinding

class StudentHome : AppCompatActivity() {
    private lateinit var binding : StudentHomeBinding
    private lateinit var sharedPref: StudentLoginSharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StudentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = StudentLoginSharedPref(this)
        sharedPref.checkLogin()

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.Help -> {
                    showToast("Help")
                }
                R.id.Feedback -> {
                    showToast("Feedback")
                }
                R.id.SignOut -> {
                    sharedPref.studentLogOut()
                }
            }
            true
        }
    }

    private fun showToast(str:String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}