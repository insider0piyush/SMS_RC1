package com.insider0piyush.sms_rc1.shared.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentSqlite(context: Context) : SQLiteOpenHelper (context,"SMS_RC1",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStudentSqlite = "CREATE TABLE STUDENT(FullName TEXT , Email TEXT PRIMARY KEY,Password TEXT)"
        db?.execSQL(createTableStudentSqlite)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS FACULTY")
        onCreate(db)
    }
}