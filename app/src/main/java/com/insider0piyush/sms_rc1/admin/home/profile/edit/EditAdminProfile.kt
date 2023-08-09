package com.insider0piyush.sms_rc1.admin.home.profile.edit

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insider0piyush.sms_rc1.admin.home.user.viewModel.GetAdminViewModel
import com.insider0piyush.sms_rc1.admin.util.sharedpref.AdminLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.EditAdminProfileBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite

class EditAdminProfile : AppCompatActivity() {
    private lateinit var binding: EditAdminProfileBinding
    private lateinit var sharedPref: AdminLoginSharedPref
    private lateinit var adminSqlite: AdminSqlite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = AdminLoginSharedPref(this)
        adminSqlite = AdminSqlite(this)

        val email = sharedPref.sharedPreferences.getString("email","")
        if(email != null){
            val user = adminSqlite.getUserDetail(email)
                binding.EditEmail.setText(user[0].Email)
                binding.EditFullName.setText(user[0].FullName)
                Log.d("GData",
                    sharedPref.sharedPreferences.getString("email","")
                        ?.let { adminSqlite.getUserDetail(it) }.toString())
        }else{
            showToast("Something went wrong")
        }
        binding.AdminUpdate.setOnClickListener {
            updateStudent()
        }
    }

    private fun updateStudent(){
        val email = sharedPref.sharedPreferences.getString("email","")
        if(email!= null){
            val user = adminSqlite.getUserDetail(email)
            val edtEmail = binding.EditEmail.text.toString()
            val edtName = binding.EditFullName.text.toString()

            if(edtEmail == user[0].Email && edtName == user[0].FullName){
                showToast("Data can't be changed")
            }else{
                changedData()
            }
        }
    }

    private fun changedData(){
        val adName = binding.EditFullName.text.toString()
        val adMail = binding.EditEmail.text.toString()

        if(adName.isEmpty() || adMail.isEmpty()){
            showToast("Fields can't empty")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(adMail).matches()){
            showToast("Enter valid mail")
        }else {
            val getAdminViewModel = GetAdminViewModel(
                FullName = adName,
                Email = adMail
            )
            val updateAdmin = adminSqlite.updateAdminData(getAdminViewModel)
            if(updateAdmin > -1){
                showToast("Update")
            }else{
                showToast("Not Update")
            }
        }
    }


    private fun showToast(str : String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}