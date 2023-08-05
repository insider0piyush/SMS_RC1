package com.insider0piyush.sms_rc1.admin.home.user.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.insider0piyush.sms_rc1.admin.home.user.adapter.StudentAdapter
import com.insider0piyush.sms_rc1.databinding.ActivityStudentListBinding
import com.insider0piyush.sms_rc1.shared.db.AdminSqlite

class StudentList : AppCompatActivity() {
    private lateinit var binding : ActivityStudentListBinding
    private lateinit var adminSqlite: AdminSqlite
    private var studentAdapter : StudentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter()
        binding.recyclerView.adapter = studentAdapter
        adminSqlite = AdminSqlite(this)

        val studentList = adminSqlite.getAllStudent()
        studentAdapter?.studentAdd(studentList)

    }
}