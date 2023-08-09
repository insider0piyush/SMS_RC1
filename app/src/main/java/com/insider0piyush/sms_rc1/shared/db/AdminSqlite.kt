package com.insider0piyush.sms_rc1.shared.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.insider0piyush.sms_rc1.admin.home.user.viewModel.FacultyViewModel
import com.insider0piyush.sms_rc1.admin.home.user.viewModel.GetAdminViewModel
import com.insider0piyush.sms_rc1.admin.home.user.viewModel.StudentViewModel
import com.insider0piyush.sms_rc1.admin.util.AdminModel
import com.insider0piyush.sms_rc1.shared.model.FacultyModel
import com.insider0piyush.sms_rc1.shared.model.StudentModel

class AdminSqlite(con : Context) : SQLiteOpenHelper(con,"SMS_RC1",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ADMIN (FullName VARCHAR(42),Email VARCHAR(256) PRIMARY KEY, Password VARCHAR(32) ) "
        val createTableStudentSqlite = "CREATE TABLE STUDENT(FirstName VARCHAR(12) NOT NULL , MiddleName VATCHAR(22) , LastName VARCHAR(17) NOT NULL,Email VARCHAR(52) PRIMARY KEY , DateOfBirth VARCHAR(27) NOT NULL, MobileNumber VARCHAR(15) UNIQUE NOT NULL, Sid VARCHAR(6) UNIQUE NOT NULL ,WebUrl VARCHAR(128) , Address1 VARCHAR(72) , Address2 VARCHAR(72) , State VARCHAR(17) , City VARCHAR(17) , PostalCode VARCHAR(6) , Password VARCHAR(42))"
        val createTableFacultySqlite = "CREATE TABLE FACULTY(FirstName VARCHAR(12) NOT NULL , MiddleName VATCHAR(22) , LastName VARCHAR(17) NOT NULL,Email VARCHAR(52) PRIMARY KEY , DateOfBirth VARCHAR(27) NOT NULL, MobileNumber VARCHAR(15) UNIQUE NOT NULL, Fid VARCHAR(6) UNIQUE NOT NULL ,WebUrl VARCHAR(128) , Address1 VARCHAR(72) , Address2 VARCHAR(72) , State VARCHAR(17) , City VARCHAR(17) , PostalCode VARCHAR(6) , Password VARCHAR(42))"
        db?.execSQL(createTable)
        db?.execSQL(createTableStudentSqlite)
        db?.execSQL(createTableFacultySqlite)
    }

    override fun onUpgrade(db: SQLiteDatabase?, val1: Int, val2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ADMIN ")
        db?.execSQL("DROP TABLE IF EXISTS STUDENT")
        db?.execSQL("DROP TABLE IF EXISTS FACULTY")
        onCreate(db)
    }

    //Admin -------------------------->

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

    fun getUserDetail(Email : String) : ArrayList<GetAdminViewModel>{

        val admin : ArrayList<GetAdminViewModel> = ArrayList()
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM ADMIN WHERE EMAIL = '$Email' ",null)

        if(cursor.moveToNext()){
            do{
                var fullname = cursor.getString(cursor.getColumnIndex("FullName"))
                val email = cursor.getString(cursor.getColumnIndex("Email"))

                val user = GetAdminViewModel(fullname,email)
                admin.add(user)
            }while (cursor.moveToNext())
        }
        return admin
    }

    fun updateAdminData(getAdminViewModel: GetAdminViewModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("FullName",getAdminViewModel.FullName)
        contentValues.put("Email",getAdminViewModel.Email)
        val email = getAdminViewModel.Email

        val queryExe = db.update("ADMIN",contentValues,"Email = "+ "'$email'",null)
        db.close()
        return queryExe
    }
    //Student -------------------->
    fun registerStudent(studentModel: StudentModel) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("FirstName", studentModel.Fname)
        contentValues.put("MiddleName",studentModel.Mname)
        contentValues.put("LastName",studentModel.Lname)
        contentValues.put("Email",studentModel.Email)
        contentValues.put("DateOfBirth",studentModel.DateOfBirth)
        contentValues.put("MobileNumber",studentModel.Mno)
        contentValues.put("Sid",studentModel.Sid)
        contentValues.put("WebUrl",studentModel.WebUrl)
        contentValues.put("Address1",studentModel.Address1)
        contentValues.put("Address2",studentModel.Address2)
        contentValues.put("State",studentModel.State)
        contentValues.put("City",studentModel.City)
        contentValues.put("PostalCode",studentModel.PostalCode)
        contentValues.put("Password",studentModel.Password)

        val success = db.insert("STUDENT",null,contentValues)
        db.close()
        return success
    }

    fun toCheckRegisterStudentAlreadyExists(Email: String) : Boolean{
        val db= this.writableDatabase
        val cursor:Cursor?

        cursor = db.rawQuery("SELECT * FROM STUDENT WHERE Email='$Email' ",null)
        if(cursor.count>0){
            return true
        }
        return false
    }

    fun checkStdudentIsValid(Email:String , Password:String ,Sid :String) : Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM STUDENT WHERE SID='$Sid' AND EMAIL='$Email' AND PASSWORD='$Password' ",null)
        if(cursor.count>0){
            return true
        }
        return false
    }

    fun getAllStudent() : ArrayList<StudentViewModel>{
        val studentList : ArrayList<StudentViewModel> = ArrayList()
        val db = this.writableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery("SELECT FirstName,MiddleName,LastName,Email FROM STUDENT ", null)
        }catch (e:SQLiteException){
            e.printStackTrace()
            db.execSQL("SELECT FirstName,MiddleName,LastName,Email FROM STUDENT ")
            return ArrayList()
        }

        var studentFirstName : String
        var studentLastName : String
        var fullName : String
        var studentEmail : String

        if(cursor.moveToFirst()){
            do{
                studentFirstName = cursor.getString(cursor.getColumnIndex("FirstName"))
                studentLastName = cursor.getString(cursor.getColumnIndex("LastName"))
                studentEmail = cursor.getString(cursor.getColumnIndex("Email"))
                fullName = "$studentFirstName $studentLastName"

                val student = StudentViewModel(fullName,studentEmail)
                studentList.add(student)
            }while (cursor.moveToNext())
        }
        return studentList
    }

    //Faculty -------------------->
    fun registerFaculty(facultyModel: FacultyModel) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("FirstName", facultyModel.Fname)
        contentValues.put("MiddleName",facultyModel.Mname)
        contentValues.put("LastName",facultyModel.Lname)
        contentValues.put("Email",facultyModel.Email)
        contentValues.put("DateOfBirth",facultyModel.DateOfBirth)
        contentValues.put("MobileNumber",facultyModel.Mno)
        contentValues.put("Fid",facultyModel.Fid)
        contentValues.put("WebUrl",facultyModel.WebUrl)
        contentValues.put("Address1",facultyModel.Address1)
        contentValues.put("Address2",facultyModel.Address2)
        contentValues.put("State",facultyModel.State)
        contentValues.put("City",facultyModel.City)
        contentValues.put("PostalCode",facultyModel.PostalCode)
        contentValues.put("Password",facultyModel.Password)

        val success = db.insert("FACULTY",null,contentValues)
        db.close()
        return success
    }

    fun checkFacultyEmailIsExists(Email: String) : Boolean{
        val db= this.writableDatabase
        val cursor:Cursor?

        cursor = db.rawQuery("SELECT * FROM FACULTY WHERE Email='$Email' ",null)
        if(cursor.count>0){
            return true
        }
        return false
    }

    fun checkFacultyLogin(Email:String , Password:String ,Fid :String) : Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM FACULTY WHERE FID='$Fid' AND EMAIL='$Email' AND PASSWORD='$Password' ",null)
        if(cursor.count>0){
            return true
        }
        return false
    }

    fun getAllFacultyList() : ArrayList<FacultyViewModel>{
        val facultyList : ArrayList<FacultyViewModel> = ArrayList()
        val db = this.writableDatabase

        val cursor: Cursor?

        try {
           cursor = db.rawQuery("SELECT FirstName,MiddleName,LastName,Email FROM FACULTY ", null)
        }catch (e:SQLiteException){
            e.printStackTrace()
            db.execSQL("SELECT FirstName,MiddleName,LastName,Email FROM FACULTY ")
            return ArrayList()
        }

        var facultyFirstName : String
        var facultyLastName : String
        var fullName : String
        var facultyEmail : String

        if(cursor.moveToFirst()){
            do{
                facultyFirstName = cursor.getString(cursor.getColumnIndex("FirstName"))
                facultyLastName = cursor.getString(cursor.getColumnIndex("LastName"))
                facultyEmail = cursor.getString(cursor.getColumnIndex("Email"))
                fullName = "$facultyFirstName $facultyLastName"

                val faculty = FacultyViewModel(fullName,facultyEmail)
                facultyList.add(faculty)
            }while (cursor.moveToNext())
        }
        return facultyList
    }
}