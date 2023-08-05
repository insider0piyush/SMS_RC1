package com.insider0piyush.sms_rc1.admin.home.user.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.util.sharedpref.FacultyLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.ActivityFacultyBinding
import com.insider0piyush.sms_rc1.faculty.FacultyHome
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite

class Faculty : AppCompatActivity() {
    private lateinit var binding: ActivityFacultyBinding
    private lateinit var adminSqlite: AdminSqlite
    private lateinit var sharedPref: FacultyLoginSharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adminSqlite = AdminSqlite(this )
        sharedPref = FacultyLoginSharedPref(this)

        if(sharedPref.isFacultyLogin()){
            startActivity(Intent(applicationContext,FacultyHome::class.java).setAction(Intent.ACTION_VIEW).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }

        binding.ForgotPassword.setOnClickListener {
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
                .setTitle("Information")
                .setMessage("Contact to admin to reset your password ! ")
                .setPositiveButton("OK"
                ) { dialogInterface, _ -> dialogInterface.dismiss()}
            materialAlertDialogBuilder.create().show()
        }

        binding.btnLogin.setOnClickListener {
            loginFaculty()
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
    private fun loginFaculty(){

        val fid = binding.EditFid.text.toString()
        val email = binding.EditEmail.text.toString()
        val password = binding.EditPassword.text.toString()

        if(fid.isEmpty()){
            showToast("Please enter fid")
        }else if (email.isEmpty()){
            showToast("Enter your email")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showToast("Enter valid email")
        }else if (password.isEmpty()){
            showToast("Password cannot empty")
        }else{
            val checkStudent = adminSqlite.checkFacultyLogin(Email = email, Password = password, Fid = fid)
            if(checkStudent){
                sharedPref.facultyLogin(email,fid)
                binding.EditFid.text?.clear()
                binding.EditEmail.text?.clear()
                binding.EditPassword.text?.clear()
                showToast("Login Successfully")
                startActivity(Intent(this, FacultyHome::class.java).setAction(Intent.ACTION_VIEW))
            }else{
                showToast("Something went wrong")
            }
        }
    }
    private fun showToast(str:String){
        Toast.makeText(applicationContext,str, Toast.LENGTH_SHORT).show()
    }
}