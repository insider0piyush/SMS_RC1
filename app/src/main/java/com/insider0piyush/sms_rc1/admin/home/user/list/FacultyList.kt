package com.insider0piyush.sms_rc1.admin.home.user.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.insider0piyush.sms_rc1.admin.home.user.adapter.FacultyAdapter
import com.insider0piyush.sms_rc1.databinding.ActivityFacultyListBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite

class FacultyList : AppCompatActivity() {
    private lateinit var binding: ActivityFacultyListBinding
    private lateinit var adminSqlite: AdminSqlite
    private var facultyAdapter : FacultyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adminSqlite= AdminSqlite(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        facultyAdapter = FacultyAdapter()
        binding.recyclerView.adapter = facultyAdapter

        val facultyList = adminSqlite.getAllFacultyList()
        facultyAdapter?.addFaculty(facultyList)
    }
}