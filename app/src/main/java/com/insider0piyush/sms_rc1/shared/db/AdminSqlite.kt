package com.insider0piyush.sms_rc1.shared.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.insider0piyush.sms_rc1.admin.util.AdminModel

class AdminSqlite(con : Context) : SQLiteOpenHelper(con,"SMS_RC1",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ADMIN (FullName VARCHAR(42),Email VARCHAR(72) PRIMARY KEY, Password VARCHAR(32) ) "
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, val1: Int, val2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ADMIN ")
        onCreate(db)
    }

    fun createAdmin(adminModel: AdminModel) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("FullName",adminModel.FullName)
        contentValues.put("Email",adminModel.Email)
        contentValues.put("Password",adminModel.Password)

        val isComplete = db.insert("ADMIN",null,contentValues)
        db.close()
        return isComplete
    }

    fun toCheckAdminIsValidOrNot(Email : String, Password : String) : Boolean{
        val db = this.writableDatabase
        val cursor: Cursor?

        cursor =db.rawQuery("SELECT * FROM ADMIN WHERE EMAIL='$Email' AND PASSWORD='$Password' ", null)
        if(cursor?.count!! > 0){
            return true
        }
        return false
    }

    fun toCheckAdminIsExists(Email : String) : Boolean{
        val db = this.writableDatabase
        val cursor:Cursor?

        cursor= db.rawQuery("SELECT * FROM ADMIN WHERE EMAIL= '$Email'",null)
        if(cursor?.count!! > 0){
            return true
        }
        return false
    }
}