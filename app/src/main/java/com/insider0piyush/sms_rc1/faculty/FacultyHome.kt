package com.insider0piyush.sms_rc1.faculty

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.util.sharedpref.FacultyLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.FacultyHomeBinding

class FacultyHome : AppCompatActivity() {
    private lateinit var binding: FacultyHomeBinding
    private lateinit var sharedPref: FacultyLoginSharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = FacultyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = FacultyLoginSharedPref(this)

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
                    sharedPref.facultyLogOut()
                }
            }
            true
        }
    }
    private fun showToast(str:String){
        Toast.makeText(applicationContext,str, Toast.LENGTH_SHORT).show()
    }
}