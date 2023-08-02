package com.insider0piyush.sms_rc1.admin.home.user

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.AdminHome
import com.insider0piyush.sms_rc1.databinding.ActivityStudentAddBinding

class StudentAdd : AppCompatActivity() {
    private lateinit var binding: ActivityStudentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val states = resources.getStringArray(R.array.States)
        val GujaratCities = resources.getStringArray(R.array.GujaratCities)

        val arrayAdapterCity = ArrayAdapter(this,R.layout.material_dropdown_exposedmenu,GujaratCities)
        val arrayAdapterState = ArrayAdapter(this,R.layout.material_dropdown_exposedmenu,states)

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
            val materialAlertDialogBuilder: MaterialAlertDialogBuilder =
                MaterialAlertDialogBuilder(this)
                    .setMessage("Your changes have not been saved")
                    .setPositiveButton("Save") { _, _ ->
                        showToast("Saved !")
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

        binding.ButtonAddStudent.setOnClickListener {
            showToast(getString(R.string.useradd))
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

    private fun showToast(str : String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}