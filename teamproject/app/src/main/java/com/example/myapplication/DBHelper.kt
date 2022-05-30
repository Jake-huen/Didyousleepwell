package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version){


    //데이터베이스가 만들어 지지않은 상태에서만 작동. 이미 만들어져 있는 상태라면 실행X
    override fun onCreate(db: SQLiteDatabase) {

        // sleep_time 테이블 생성
        val sql: String = "CREATE TABLE if not exists sleep_time(" +
                "id integer primary key autoincrement," +
                "recommend_time datetime," +
                "recommend_flag Integer);"

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



    /*
    insert SleepTIme Data 메소드
    DataSleepTime 객체를 주시면 Insert 됩니다.
    ex) val data = DataSleepTime(0, "2008-04-28 09:15:42", 0)
    ex) val data2 = DataSleepTime(0, "2022-05-26 15:00:13", 1)
    ex) val data3 = DataSleepTime(0, "2021-04-26 14:00:58", 0)
    insertSleepTimeData(data)
    insertSleepTimeData(data2)
    insertSleepTimeData(data3)

            /* dbHelper 사용법도 추가 */
            dbHelper = DBHelper(this, "dysw.db", null, 1)
            val data = DataSleepTime(0, "2008-04-28 09:15:42", 0)
            dbHelper.insertSleepTimeData(data)

    */
    fun insertSleepTimeData(data:DataSleepTime){
        val values = ContentValues()
        // "테이블이름", 정보
        values.put("recommend_time",data.recommendTime)
        values.put("recommend_flag",data.recommend_flag)
        val wd = writableDatabase
        wd.insert("sleep_time",null,values)
        wd.close()
    }

    //insert User Data 메소드
    fun insertUserData(age:Int, minute:Int){
        val values = ContentValues()
        //매개변수 지정  // "테이블이름", 정보
        values.put("age",age)
        values.put("time_fall_sleep",minute)
        //쓰기나 수정이 가능한 데이터베이스 변수
        val wd = writableDatabase
        wd.insert("user",null,values)
        //사용이 끝나면 반드시 close()를 사용하여 메모리누수 가 되지않도록 합시다.
        wd.close()
    }


    //select 메소드 밑에는 사용법.
//    val list = dbHelper.selectSleepTime()
//    Log.e("error", list.get(0).recommendTime)
    fun selectSleepTime():MutableList<DataSleepTime>{
        val list = mutableListOf<DataSleepTime>()
        //전체조회
        val selectAll = "select * from sleep_time"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        // 반복문을 사용하여 list 에 데이터를 넘겨 줍니당
        // 빨간 줄이어도 무시해주도록 합시당
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val recommend_time = cursor.getString(cursor.getColumnIndex("recommend_time"))
            val recoomend_flag = cursor.getInt(cursor.getColumnIndex("recommend_flag"))

            list.add(DataSleepTime(id, recommend_time, recoomend_flag))
        }
        cursor.close()
        rd.close()

        return list
    }



    fun getAge():Int{
        Log.e("abcd", "띠용?")
        val list = mutableListOf<UserData>()
        //전체조회
        val selectAll = "select * from user"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        // 반복문을 사용하여 list 에 데이터를 넘겨 줍니당
        // 빨간 줄이어도 무시해주도록 합시당
        while(cursor.moveToNext()){
            val age = cursor.getInt(cursor.getColumnIndex("age"))
            val time_fall_sleep = cursor.getInt(cursor.getColumnIndex("time_fall_sleep"))

            list.add(UserData(age, time_fall_sleep))
        }
        cursor.close()
        rd.close()

        return list.get(0).age
    }


    fun getTimeFallSleep():Int{
        Log.e("abcd", "띠용?")
        val list = mutableListOf<UserData>()
        //전체조회
        val selectAll = "select * from user"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        // 반복문을 사용하여 list 에 데이터를 넘겨 줍니당
        // 빨간 줄이어도 무시해주도록 합시당
        while(cursor.moveToNext()){
            val age = cursor.getInt(cursor.getColumnIndex("age"))
            val time_fall_sleep = cursor.getInt(cursor.getColumnIndex("time_fall_sleep"))

            list.add(UserData(age, time_fall_sleep))
        }
        cursor.close()
        rd.close()

        return list.get(0).time_fall_sleep
    }
}