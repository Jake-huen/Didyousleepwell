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
        initLayout()
        isDB_create()
    }


    private fun isDB_create() {
        val dbfile = getDatabasePath("dysw.db")
        if(!dbfile.exists()){ // DB파일이 없으면 Init 화면으로 이동.
            val intent = Intent(this, InitActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initLayout() {
        //깰시간 정해주는 버튼
        binding.button1.setOnClickListener {
            val intent = Intent(this, RecommendupActivity::class.java)
            startActivity(intent)
        }
        //잘시간 벙해주는 버튼
        binding.button2.setOnClickListener {
            val intent = Intent(this, RecommenddownActivity::class.java)
            startActivity(intent)
        }
    }
}