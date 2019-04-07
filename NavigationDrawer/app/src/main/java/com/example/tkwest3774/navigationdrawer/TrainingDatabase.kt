package com.example.tkwest3774.navigationdrawer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tkwest3774.navigationdrawer.Model.Training
import com.example.tkwest3774.navigationdrawer.Model.User
import java.text.SimpleDateFormat
import java.util.*



private const val DB_NAME="T_TRAINING"
private const val DB_VERSION=1

fun insertTrainingInfo(context: Context, training:Training):Boolean{
    val database= TrainingDatabase(context).writableDatabase



        //今日の日付を取得する
        val today = Date()
        val df = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val inputday = df.format(today)

        database.use { db ->
            val record = ContentValues().apply {
                put("username", training.Username)
                put("type", training.Type)
                put("weight", training.Weight)
                put("rep", training.Rep)
                put("inputday", training.inputday)
                put("newfrag", 0)
            }
            database.insert("T_TRAINING", null, record)
        }
        return true

}
//トレーニングの記録を取り出す
fun queryTraining(context: Context, username:String):Training{

    //読み込み用データベースを開く
    val database= TrainingDatabase(context).readableDatabase
    //データベースから取得する
    val cursor=database.query(
        "T_TRAINING",
        null,
        "username==?",
        null,
        null,
        null,
        null,
        null
    )

    val trainingData = Training()
    //レコードを取り出し、戻り値に設定する変数に格納
    cursor.use { c ->
        while (c.moveToNext()) {
            trainingData.Username=c.getString(c.getColumnIndex("username"))
            trainingData.Type=c.getString(c.getColumnIndex("type"))
            trainingData.Weight=c.getInt(c.getColumnIndex("weight"))
            trainingData.Rep=c.getInt(c.getColumnIndex("rep"))
            trainingData.inputday=c.getString(c.getColumnIndex("inputday"))

        }
    }

    database.close()
    return trainingData
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
            type TEXT NOT NULL,
            weight INTEGER NOT NULL,
            rep INTEGER NOT NULL,
            inputday TEXT NOT NULL,
            newfrag INTEGER NOT NULL);
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        //バージョン更新時のSQL発行
    }
}