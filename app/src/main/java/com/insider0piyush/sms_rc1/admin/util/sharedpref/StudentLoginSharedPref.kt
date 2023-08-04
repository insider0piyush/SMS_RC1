package com.insider0piyush.sms_rc1.admin.util.sharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.insider0piyush.sms_rc1.admin.home.user.login.Student
import com.insider0piyush.sms_rc1.student.StudentHome

class StudentLoginSharedPref(var context: Context) {

    var sharedPreferences : SharedPreferences
    var MODE_PRIVATE : Int = 0
    var editor: Editor

    init {
        this.sharedPreferences = context.getSharedPreferences("STUDENT",MODE_PRIVATE)
        this.editor = sharedPreferences.edit()
    }

    fun studentLogin(email: String,sid :String){
        editor.putBoolean("login",true)
        editor.putString("email",email)
        editor.putString("sid",sid)
        editor.commit()
    }

    fun checkLogin(){
        if(!this.isStudentLogin()){
            val i: Intent = Intent(context,StudentHome::class.java).setAction(Intent.ACTION_VIEW)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
        }
    }

    fun studentLogOut(){
        editor.clear()
        editor.commit()
        val i: Intent = Intent(context,Student::class.java)
            .setAction(Intent.ACTION_VIEW)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(i)
    }

    fun isStudentLogin() : Boolean{
        return sharedPreferences.getBoolean("login",false)
    }
}