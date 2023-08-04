package com.insider0piyush.sms_rc1.admin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.AdminHome
import com.insider0piyush.sms_rc1.admin.home.user.security.AdminSecurity
import com.insider0piyush.sms_rc1.admin.util.sharedpref.AdminLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.AdminLoginBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite
import com.insider0piyush.sms_rc1.super_admin.SuperAdminLogin

class AdminLogin : AppCompatActivity() {
    private lateinit var binding: AdminLoginBinding
    private lateinit var adminSqlite: AdminSqlite
    lateinit var sharedPref: AdminLoginSharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = AdminLoginSharedPref(this)
        adminSqlite = AdminSqlite(this)

        binding.btnLogin.setOnClickListener {
           login()
        }
        binding.NewAdmin.setOnClickListener {
            startActivity(Intent(this,AdminSecurity::class.java).setAction(Intent.ACTION_VIEW))
        }
        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.SuperAdmin -> {
                    startActivity(Intent(this,SuperAdminLogin::class.java).setAction(Intent.ACTION_VIEW))
                }
            }
            false
        }
        if(sharedPref.isLogin()){
            startActivity(Intent(applicationContext,AdminHome::class.java).setAction(Intent.ACTION_VIEW).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }
    }

    private fun login(){
        val email = binding.EditEmail.text.toString()
        val password = binding.EditPassword.text.toString()

        if(email.isEmpty()){
            showToast("Please Enter Email")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.EditEmail.text.toString()).matches()){
            showToast("Enter Valid Email")
        }else if (password.isEmpty()){
            showToast("Enter Your Password")
        }else {
            val adminIsValidate =
                adminSqlite.toCheckAdminIsValidOrNot(Email = email, Password = password)
            if (adminIsValidate) {
                sharedPref.createLogin(email)
                startActivity(Intent(this, AdminHome::class.java).setAction(Intent.ACTION_VIEW))
                showToast("Login Successfully")
                binding.EditEmail.text?.clear()
                binding.EditPassword.text?.clear()
            } else {
                binding.EditPassword.text?.clear()
                showToast("Something went wrong")
            }
        }
    }


    private fun showToast(string : String){
        Toast.makeText(applicationContext,string,Toast.LENGTH_SHORT).show()
    }

}