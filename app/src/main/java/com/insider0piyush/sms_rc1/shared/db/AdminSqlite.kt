package com.insider0piyush.sms_rc1.shared.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.insider0piyush.sms_rc1.admin.util.AdminModel
import com.insider0piyush.sms_rc1.shared.model.StudentModel

class AdminSqlite(con : Context) : SQLiteOpenHelper(con,"SMS_RC1",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ADMIN (FullName VARCHAR(42),Email VARCHAR(72) PRIMARY KEY, Password VARCHAR(32) ) "
        val createTableStudentSqlite = "CREATE TABLE STUDENT(FirstName VARCHAR(12) NOT NULL , MiddleName VATCHAR(22) , LastName VARCHAR(17) NOT NULL,Email VARCHAR(52) PRIMARY KEY , DateOfBirth VARCHAR(27) NOT NULL, MobileNumber VARCHAR(15) UNIQUE NOT NULL, WebUrl VARCHAR(128) , Address1 VARCHAR(72) , Address2 VARCHAR(72) , State VARCHAR(17) , City VARCHAR(17) , PostalCode VARCHAR(6))"
        db?.execSQL(createTable)
        db?.execSQL(createTableStudentSqlite)

    }

    override fun onUpgrade(db: SQLiteDatabase?, val1: Int, val2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ADMIN ")
        db?.execSQL("DROP TABLE IF EXISTS STUDENT")
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
    fun registerStudent(studentModel: StudentModel) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("FirstName", studentModel.Fname)
        contentValues.put("MiddleName",studentModel.Mname)
        contentValues.put("LastName",studentModel.Lname)
        contentValues.put("Email",studentModel.Email)
        contentValues.put("DateOfBirth",studentModel.DateOfBirth)
        contentValues.put("MobileNumber",studentModel.Mno)
        contentValues.put("WebUrl",studentModel.WebUrl)
        contentValues.put("Address1",studentModel.Address1)
        contentValues.put("Address2",studentModel.Address2)
        contentValues.put("State",studentModel.State)
        contentValues.put("City",studentModel.City)
        contentValues.put("PostalCode",studentModel.PostalCode)

        val success = db.insert("STUDENT",null,contentValues)
        db.close()
        return success
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