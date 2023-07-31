package com.insider0piyush.sms_rc1.admin.util.sharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.insider0piyush.sms_rc1.admin.home.AdminHome

class AdminLoginSharedPref(var context: Context) {

    var  sharedPreferences: SharedPreferences
    var editor: Editor
    var MODE_PRIVATE : Int = 0

    init {
        this.sharedPreferences = context.getSharedPreferences("User",MODE_PRIVATE)
        this.editor = sharedPreferences.edit()
    }

    fun createLogin(email : String){
        editor.putBoolean("login",true)
        editor.putString("email",email)
        editor.commit()
    }

    fun checkLogin(){
        if(!this.isLogin()){
            val i : Intent = Intent(context,AdminHome::class.java).setAction(Intent.ACTION_VIEW)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }

    fun isLogin() : Boolean{
        return  sharedPreferences.getBoolean("login",false)
    }
}