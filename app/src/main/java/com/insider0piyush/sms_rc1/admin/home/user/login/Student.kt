package com.insider0piyush.sms_rc1.admin.home.user.login

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.databinding.ActivityStudentBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite

class Student : AppCompatActivity() {
    private lateinit var binding: ActivityStudentBinding
    private lateinit var adminSqlite: AdminSqlite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adminSqlite = AdminSqlite(this)

        binding.ForgotPassword.setOnClickListener {
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
                .setTitle("Information")
                .setMessage("Contact to admin to reset your password ! ")
                .setPositiveButton("OK"
                ) { dialogInterface, _ -> dialogInterface.dismiss()}
            materialAlertDialogBuilder.create().show()
        }

        binding.btnLogin.setOnClickListener {
            loginStudent()
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.Help -> {

                }
                R.id.Feedback -> {

                }
            }
            true
        }
    }
    private fun loginStudent(){

        val sid = binding.EditSid.text.toString()
        val email = binding.EditEmail.text.toString()
        val password = binding.EditPassword.text.toString()

        if(sid.isEmpty()){
            showToast("Please enter sid")
        }else if (email.isEmpty()){
            showToast("Enter your email")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showToast("Enter valid email")
        }else if (password.isEmpty()){
            showToast("Password cannot empty")
        }else{
            val checkStudent = adminSqlite.checkStdudentIsValid(Email = email, Password = password, Sid = sid)
            if(checkStudent){
                showToast("Login Successfully")
            }else{
                showToast("Something went wrong")
            }
        }
    }
    private fun showToast(str:String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}