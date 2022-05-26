//메인 화면
package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isDB_create()
        initLayout()
    }


    private fun isDB_create() {
        val dbfile = getDatabasePath("dysw.db")
        if(!dbfile.exists()){ // DB파일이 없으면 데이터베이스 생성.
            dbHelper = DBHelper(this, "dysw.db", null, 1)
            getDatabasePath("dysw.db")
            database = dbHelper.writableDatabase
            val age = intent.getIntExtra("age", 0)
            val minute = intent.getIntExtra("minute", 0)
            Log.e("error",age.toString())
            Log.e("error",minute.toString())

//            TODO insert 안댐 흑흑
//            insertUserRecord(age,minute)
        }
    }

    fun insertUserRecord(age : Int, time_fall_sleep : Int){

        val sql = "INSERT INTO user (name, age) VALUES (" +
                age + "', "+
                time_fall_sleep + ");"

        database.execSQL(sql)

    }

    private fun initLayout() {
        //깰시간 정해주는 버튼
        binding.button1.setOnClickListener {
            val intent = Intent(this, RecommendupActivity::class.java)
            startActivity(intent)
        }
//        확인하기용ss
        //잘시간 벙해주는 버튼
        binding.button2.setOnClickListener {
            val intent = Intent(this, RecommenddownActivity::class.java)
            startActivity(intent)
        }
    }
}