package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class InitFragment4 : Fragment() {

    lateinit var dbHelper: DBHelper
    lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_init4, container, false)
        val title = view.findViewById<TextView>(R.id.title4)
        val startBtn = view.findViewById<Button>(R.id.startButton)

//      시작하기 버튼 작업 TODO -> 데이터베이스 생성 후 테이블 생성 작업
        startBtn.setOnClickListener {
//           인텐트 작업
            val age = view.findViewById<EditText>(R.id.editTextAge).text.toString().toInt()
            val minute = view.findViewById<EditText>(R.id.editTextDate).text.toString().toInt()
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("age", age)
            intent.putExtra("minute", minute)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
    }
}