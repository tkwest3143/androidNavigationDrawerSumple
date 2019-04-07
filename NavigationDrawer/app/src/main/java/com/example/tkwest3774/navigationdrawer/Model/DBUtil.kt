package com.example.tkwest3774.navigationdrawer.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

private const val DB_NAME="U_USER"
private const val DB_VERSION=1


class UtilDatabase(context: Context)
    : SQLiteOpenHelper(context,
    DB_NAME,null,
    DB_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        //テーブルの作成
        db?.execSQL("""
            CREATE TABLE U_User(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            real TEXT NOT NULL,
            username TEXT NOT NULL,
            birthday TEXT NOT NULL,
            inputday TEXT NOT NULL,
            updateday TEXT NOT NULL,
            dfrag INTEGER NOT NULL);
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        //バージョン更新時のSQL発行
    }
}

