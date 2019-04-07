package com.example.tkwest3774.navigationdrawer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tkwest3774.navigationdrawer.Model.User
import java.text.SimpleDateFormat
import java.util.*



private const val DB_NAME="T_TRAINING"
private const val DB_VERSION=1

fun insertTrainingInfo(context: Context, user: User):Boolean{
    val database= TrainingDatabase(context).writableDatabase

    //ユーザー名重複チェック
    if(queryTraining(context,user.username)=="") {
        //今日の日付を取得する
        val today = Date()
        val df = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val inputday = df.format(today)

        database.use { db ->
            val record = ContentValues().apply {
                put("username", user.username)
                put("real", user.realname)
                put("birthday", user.birthday)
                put("inputday", inputday)
                put("updateday", user.updateday)
                put("dfrag", 0)
            }
            database.insert("T_TRAINING", null, record)
        }
        return true
    }else{
        return false
    }
}
//トレーニングの記録を取り出す
fun queryTraining(context: Context, username:String):String{
    var name=""
    //読み込み用データベースを開く
    val database= TrainingDatabase(context).readableDatabase
    //データベースから取得する
    val cursor=database.query(
        "T_TRAINING",
        arrayOf("username"),
        "username==?",
        null,
        null,
        null,
        null,
        null
    )

    //レコードを取り出し、戻り値に設定する変数に格納
    cursor.use { c ->
        while (c.moveToNext()) {
            name = c.getString(c.getColumnIndex("username"))
        }
    }

    database.close()
    return name
}
class TrainingDatabase(context: Context)
    : SQLiteOpenHelper(context,
    DB_NAME,null,
    DB_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        //テーブルの作成
        db?.execSQL("""
            CREATE TABLE T_TRAINING(
            number INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT NOT NULL,
            weight INTEGER NOT NULL,
            rep INTEGER NOT NULL,
            inputday TEXT NOT NULL,
            newFrag INTEGER NOT NULL);
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        //バージョン更新時のSQL発行
    }



    fun insertUser(context: Context, user: User):Boolean{
        val database= TrainingDatabase(context).writableDatabase

        //ユーザー名重複チェック
        if(queryTraining(context,user.username) =="") {
            //今日の日付を取得する
            val today = Date()
            val df = SimpleDateFormat("yyyy/MM/dd HH:mm")
            val inputday = df.format(today)

            database.use { db ->
                val record = ContentValues().apply {
                    put("username", user.username)
                    put("real", user.realname)
                    put("birthday", user.birthday)
                    put("inputday", inputday)
                    put("updateday", user.updateday)
                    put("dfrag", 0)
                }
                database.insert("U_User", null, record)
            }
            return true
        }else{
            return false
        }


    }
}