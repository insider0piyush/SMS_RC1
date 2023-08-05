package com.insider0piyush.sms_rc1.admin.home.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.insider0piyush.sms_rc1.R
import com.insider0piyush.sms_rc1.admin.home.user.viewModel.FacultyViewModel

class FacultyAdapter : RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder>() {

    private var facultyList : ArrayList<FacultyViewModel> = ArrayList()

    fun addFaculty(arrayList: ArrayList<FacultyViewModel>){
        this.facultyList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FacultyViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_faculty_view,parent,false)
    )

    override fun getItemCount(): Int {
        return facultyList.size
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
      holder.bindView(facultyList[position])
    }

    class FacultyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private var facultyName = view.findViewById<MaterialTextView>(R.id.FacultyName)
        private var facultyEmailId = view.findViewById<MaterialTextView>(R.id.FacultyEmailId)

        fun bindView(facultyViewModel: FacultyViewModel){
            facultyName.text = facultyViewModel.facultyName
            facultyEmailId.text = facultyViewModel.facultyEmailId
        }
    }
}