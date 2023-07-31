package com.insider0piyush.sms_rc1.admin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.admin.util.AdminModel
import com.insider0piyush.sms_rc1.databinding.AdminSignUpBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite

class AdminSignUp : AppCompatActivity() {
    private lateinit var binding: AdminSignUpBinding
    private lateinit var adminSqlite: AdminSqlite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adminSqlite = AdminSqlite(this)

        binding.btnSignUp.setOnClickListener {
            adminSignUp()
        }

        binding.alreadyHaveAnAccount.setOnClickListener {
            startActivity(Intent(this,AdminLogin::class.java).setAction(Intent.ACTION_VIEW))
        }
    }

    private fun adminSignUp(){
        val fullName = binding.EditFullName.text.toString()
        val eMail = binding.EditEmail.text.toString()
        val passWord = binding.EditPassword.text.toString()
        val confirmPassword = binding.EditConfirmPassword.text.toString()

        if(fullName.isEmpty()){
            showToast("Enter your name")
        }else if(eMail.isEmpty()){
            showToast("Email can't be empty")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(eMail).matches()){
            showToast("Enter valid email")
        }else if(passWord.isEmpty()){
            showToast("Password is necessary")
        }else if(confirmPassword.isEmpty()){
            showToast("Confirm password can't be empty")
        }else if (passWord.toString() != confirmPassword){
            showToast("Password not match")
        }else{
            val checkAdmin = adminSqlite.toCheckAdminIsExists(eMail)
            if(!checkAdmin) {
                val adminModel = AdminModel(fullName, eMail, passWord)
                val createAdmin = adminSqlite.createAdmin(adminModel)
                if (createAdmin > -1) {
                    clearData()
                    showToast("Admin created")
                    startActivity(
                        Intent(
                            this,
                            AdminLogin::class.java
                        ).setAction(Intent.ACTION_VIEW)
                    )
                } else {
                    clearData()
                    showToast("Something went wrong")
                }
            }else{
                clearData()
                showToast("Admin already exists !")
            }
        }
    }

    private fun clearData(){
        binding.EditFullName.text?.clear()
        binding.EditEmail.text?.clear()
        binding.EditPassword.text?.clear()
        binding.EditConfirmPassword.text?.clear()
    }
    private fun showToast(string: String){
        Toast.makeText(applicationContext,string,Toast.LENGTH_SHORT).show()
    }
}