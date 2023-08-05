package com.insider0piyush.sms_rc1.admin.home.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.user.viewModel.StudentViewModel

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentList : ArrayList<StudentViewModel> = ArrayList()

    fun studentAdd(arrayList: ArrayList<StudentViewModel>){
        this.studentList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= StudentViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_student_view,parent,false)
        )

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindView(studentList[position])
    }

    class StudentViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var studentName = view.findViewById<MaterialTextView>(R.id.StudentName)
        var studentEmail = view.findViewById<MaterialTextView>(R.id.StudentEmailId)

        fun bindView(studentViewModel: StudentViewModel){
            studentEmail.text = studentViewModel.studentEmail
            studentName.text = studentViewModel.studentName
        }
    }
}