package com.insider0piyush.sms_rc1.admin.home.user.security

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.admin.AdminSignUp
import com.insider0piyush.sms_rc1.databinding.AdminSecurityBinding

class AdminSecurity : AppCompatActivity() {
    private lateinit var binding: AdminSecurityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminSecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            userValid()
        }
    }

    private fun userValid(){
        val email = binding.EditEmail.text.toString()
        val fullname = binding.EditFullName.text.toString()
        val regcode = binding.EditOrgRegNo.text.toString()

        if(regcode.isEmpty()){
            showToast("Enter Security Code")
        }else if (fullname.isEmpty()){
            showToast("Enter your name")
        } else if(email.isEmpty()){
            showToast("Enter your email")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showToast("Enter valid email")
        }else{
            if(regcode == "INSIDER0PIYUSH" || regcode == "insider0piyush" || regcode == "Insider0piyush" || regcode == "Insider0Piyush"){
                startActivity(Intent(this,AdminSignUp::class.java)
                    .setAction(Intent.ACTION_VIEW)
                    .putExtra("fullname",fullname)
                    .putExtra("email",email))
                binding.EditOrgRegNo.text?.clear()
                binding.EditFullName.text?.clear()
                binding.EditEmail.text?.clear()
            }else{
                showToast("CAN NOT CREATE ADMIN ⚠️")
            }
        }
    }

    private fun showToast(str:String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}