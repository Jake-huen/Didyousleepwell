package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version){



    override fun onCreate(db: SQLiteDatabase) {

        // sleep_time 테이블 생성
        val sql: String = "CREATE TABLE if not exists sleep_time(" +
                "id integer primary key autoincrement," +
                "recommend_time datetime," +
                "recommend_flag boolean);"

        db.execSQL(sql)
        // user 테이블 생성
        val sql2: String = "CREATE TABLE if not exists user(" +
                "age integer," +
                "time_fall_sleep integer);"

        db.execSQL(sql2)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists sleep_time"
        db.execSQL(sql)
        onCreate(db)
    }

    fun insertUserRecord(age : Int, time_fall_sleep : Int){

        val sql = "INSERT INTO user (name, age) VALUES (" +
                 age + "', "+
                time_fall_sleep + ");"

    }
}