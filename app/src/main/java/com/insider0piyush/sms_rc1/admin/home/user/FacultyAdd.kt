package com.insider0piyush.sms_rc1.admin.home.user

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.AdminHome
import com.insider0piyush.sms_rc1.databinding.ActivityFacultyAddBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite
import com.insider0piyush.sms_rc1.shared.model.FacultyModel

class FacultyAdd : AppCompatActivity() {
    private lateinit var binding : ActivityFacultyAddBinding
    private lateinit var facultySqlite: AdminSqlite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        facultySqlite= AdminSqlite(this)
        val states = resources.getStringArray(R.array.States)
        val GujaratCities = resources.getStringArray(R.array.GujaratCities)

        val arrayAdapterCity = ArrayAdapter(this,
            R.layout.material_dropdown_exposedmenu,GujaratCities)
        val arrayAdapterState = ArrayAdapter(this, R.layout.material_dropdown_exposedmenu,states)

        binding.EditState.setAdapter(arrayAdapterState)
        binding.EditCity.setAdapter(arrayAdapterCity)

        binding.TopAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.Help -> {
                    showToast("Help !")
                }

                R.id.Feedback -> {
                    showToast("Feedback !")
                }
            }
            true
        }
        binding.TopAppBar.setNavigationOnClickListener {
            facultyRegisterDialog()
        }

        binding.ButtonAddStudent.setOnClickListener {
            registerFaculty()
            finish()
        }

        binding.EditDateOfBirth.setOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Choose your birthdate !")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .build()

            materialDatePicker.addOnPositiveButtonClickListener {
                binding.EditDateOfBirth.setText(materialDatePicker.headerText)
            }
            materialDatePicker.show(supportFragmentManager,"Material date picker")
        }
    }

    private fun facultyRegisterDialog() {
        val materialAlertDialogBuilder: MaterialAlertDialogBuilder =
            MaterialAlertDialogBuilder(this)
                .setMessage("Your changes have not been saved")
                .setPositiveButton("Save") { _, _ ->
                    registerFaculty()
                    finish()
                }
                .setNegativeButton("Discard") { _, _ ->
                    showToast("Discard")
                    startActivity(
                        Intent(
                            this,
                            AdminHome::class.java
                        ).setAction(Intent.ACTION_VIEW)
                    )
                }
        materialAlertDialogBuilder.create().show()
    }

    private fun registerFaculty() {
        val Fname = binding.EditFname.text.toString()
        val Mname = binding.EditMname.text.toString()
        val Lname = binding.EditLname.text.toString()
        val Email = binding.EditEmail.text.toString()
        val DateOfBirth = binding.EditDateOfBirth.text.toString()
        val MobileNumber = binding.EditMobilePhone.text.toString()
        val Fid= binding.EditFid.text.toString()
        val WebUrl = binding.EditWebsiteLink.text.toString()
        val Address1 = binding.EditTextAddress.text.toString()
        val Address2 = binding.EditAddress2.text.toString()
        val State = binding.EditState.text.toString()
        val City = binding.EditCity.text.toString()
        val PostalCode = binding.EditPincode.text.toString()
        val Password = "$Fname@123"


        if(Fname.isEmpty()){
            showToast("First name is not empty")
        }else if (Lname.isEmpty()){
            showToast("Last name is not empty")
        }else if(Email.isEmpty()){
            showToast("Email is necessary")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            showToast("Enter Valid Email")
        }else if(DateOfBirth.isEmpty()){
            showToast("Can't be empty dateOfBirth")
        }else if(MobileNumber.isEmpty()){
            showToast("Mobile number can't be empty")
        }else if(!Patterns.PHONE.matcher(MobileNumber).matches()){
            showToast("Enter valid phone no ")
        }else if(Fid.isEmpty()){
            showToast("Enter your sid")
        } else {
            val checkFacultyEntry = facultySqlite.checkFacultyEmailIsExists(Email)
            if(!checkFacultyEntry){
                val facultyModel = FacultyModel(Fname, Mname,Lname, Email, DateOfBirth,MobileNumber,Fid,WebUrl, Address1, Address2, State, City,PostalCode,Password)
                val createFaculty = facultySqlite.registerFaculty(facultyModel)
                if(createFaculty > -1){
                    showToast("Faculty added successfully")
                    binding.EditFname.text?.clear()
                    binding.EditMname.text?.clear()
                    binding.EditLname.text?.clear()
                    binding.EditEmail.text?.clear()
                    binding.EditDateOfBirth.text?.clear()
                    binding.EditMobilePhone.text?.clear()
                    binding.EditFid.text?.clear()
                    binding.EditWebsiteLink.text?.clear()
                    binding.EditTextAddress.text?.clear()
                    binding.EditAddress2.text?.clear()
                    binding.EditState.text.clear()
                    binding.EditCity.text.clear()
                    binding.EditPincode.text?.clear()
                }else{
                    showToast("Something went wrong")
                }
            }else{
                showToast("Faculty email already exits")
            }
        }
    }



    private fun showToast(str : String){
        Toast.makeText(applicationContext,str, Toast.LENGTH_SHORT).show()
    }
}