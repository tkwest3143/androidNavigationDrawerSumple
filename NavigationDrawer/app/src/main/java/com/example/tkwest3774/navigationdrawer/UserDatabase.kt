package com.example.tkwest3774.navigationdrawer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tkwest3774.navigationdrawer.Model.User
import java.text.SimpleDateFormat
import java.util.*

private const val DB_NAME="U_USER"
private const val DB_VERSION=1

class DatabaseOpenHelper(context: Context)
    :SQLiteOpenHelper(context,
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

    //ユーザー名を取り出すメソッド
    fun queryUser(context: Context,username:String):String{
        var name=""
        //読み込み用データベースを開く
        val database= DatabaseOpenHelper(context).readableDatabase
        //データベースから取得する
        val cusor=database.query(
            "U_User",
            arrayOf("name"),
            "name==?",
            null,
            null,
            null,
            null,
            null
        )

        //レコードを取り出し、戻り値に設定する変数に格納
        cusor.use { c ->
            while (c.moveToNext()) {
                name = c.getString(c.getColumnIndex("username"))
            }
        }

        database.close()
        return name
    }

    fun insertUser(context: Context,user:User):Boolean{
        val datebase= DatabaseOpenHelper(context).writableDatabase

        //ユーザー名重複チェック
        if(queryUser(context,user.username)=="") {
            //今日の日付を取得する
            val today = Date()
            val df = SimpleDateFormat("yyyy/MM/dd HH:mm")
            val inputday = df.format(today)

            datebase.use { db ->
                val record = ContentValues().apply {
                    put("username", user.username)
                    put("real", user.realname)
                    put("birthday", user.birthday)
                    put("inputday", inputday)
                    put("updateday", user.updateday)
                    put("dfrag", 0)
                }
                datebase.insert("U_User", null, record)
            }
            return true
        }else{
            return false
        }
    }
}