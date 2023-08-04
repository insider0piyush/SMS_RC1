package com.insider0piyush.sms_rc1.admin.util.sharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.insider0piyush.sms_rc1.admin.home.user.login.Faculty
import com.insider0piyush.sms_rc1.faculty.FacultyHome

class FacultyLoginSharedPref(var context: Context) {
    var sharedPreferences : SharedPreferences
    var editor : Editor
    var MODE_PRIVATE : Int = 0

    init {
        this.sharedPreferences = context.getSharedPreferences("FACULTY",MODE_PRIVATE)
        this.editor= sharedPreferences.edit()
    }

    fun facultyLogin(email: String,fid : String){
        editor.putBoolean("login",true)
        editor.putString("email",email)
        editor.putString("sid",fid)
        editor.commit()
    }

    fun checkLogin(){
        if(!this.isFacultyLogin()){
            val i:Intent = Intent(context,FacultyHome::class.java)
                i.setAction(Intent.ACTION_VIEW)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
        }
    }

    fun facultyLogOut(){
        editor.clear()
        editor.commit()
        val i : Intent = Intent(context,Faculty::class.java)
            .setAction(Intent.ACTION_VIEW)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
    }


    fun isFacultyLogin() : Boolean{
        return sharedPreferences.getBoolean("login",false)
    }
}
