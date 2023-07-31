package com.insider0piyush.sms_rc1.shared.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FacultySqlite(context: Context) : SQLiteOpenHelper(context,"SMS_RC1",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableFacultySqlite= "CREATE TABLE FACULTY(FullName TEXT , Email TEXT PRIMARY KEY , Password TEXT)"
        db?.execSQL(createTableFacultySqlite)
    }

    override fun onUpgrade(db: SQLiteDatabase?, val1: Int, val2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS FACULTY ")
        onCreate(db)
    }
}