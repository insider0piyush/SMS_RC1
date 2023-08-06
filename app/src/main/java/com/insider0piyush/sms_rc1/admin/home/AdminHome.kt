package com.insider0piyush.sms_rc1.admin.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.user.FacultyAdd
import com.insider0piyush.sms_rc1.admin.home.user.StudentAdd
import com.insider0piyush.sms_rc1.admin.home.user.list.FacultyList
import com.insider0piyush.sms_rc1.admin.home.user.list.StudentList
import com.insider0piyush.sms_rc1.admin.util.sharedpref.AdminLoginSharedPref
import com.insider0piyush.sms_rc1.databinding.AdminHomeBinding

class AdminHome : AppCompatActivity() {
    private lateinit var binding: AdminHomeBinding
    private lateinit var sharedPref: AdminLoginSharedPref
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= AdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = AdminLoginSharedPref(this)
        sharedPref.isAdminLogin()

        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.DrawerLayoutAdmin,binding.topAppBar,R.string.open,R.string.close)
        binding.DrawerLayoutAdmin.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.AdminNavigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Setting -> {
                    showToast("Setting")
                    binding.DrawerLayoutAdmin.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        binding.FloatingActionBtn.setOnClickListener {
            selectUser()
        }
        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.UserAccountProfile -> {
                    showToast("work in progress ⌛")
                }
                R.id.Help -> {
                    showToast("Help")
                }
                R.id.Feedback -> {
                    showToast("Feedback")
                }
                R.id.SignOut -> {
                    sharedPref.adminLogOut()
                }
            }
            true
        }
        binding.NoOfFaculty.setOnClickListener {
            startActivity(Intent(applicationContext,FacultyList::class.java).setAction(Intent.ACTION_VIEW))
        }
        binding.NoOfStudent.setOnClickListener {
            startActivity(Intent(applicationContext,StudentList::class.java).setAction(Intent.ACTION_VIEW))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionBarDrawerToggle.onOptionsItemSelected(item)
    }

    private fun selectUser(){
        val user = arrayOf(resources.getString(R.string.student),   resources.getString(R.string.faculty))
        val builder = MaterialAlertDialogBuilder(this)
        with(builder){
            builder.setItems(user){
                    _, it -> when(it){
                    0 -> {
                        startActivity(Intent(context,StudentAdd::class.java).setAction(Intent.ACTION_VIEW))
                    }
                    1 -> {
                        startActivity(Intent(context,FacultyAdd::class.java).setAction(Intent.ACTION_VIEW))

                    }
                }
            }
        }
        builder.create().show()
    }

    private fun showToast(str:String){
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }
}