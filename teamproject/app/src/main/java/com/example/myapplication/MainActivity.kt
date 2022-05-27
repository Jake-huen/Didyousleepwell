//메인 화면
package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import java.time.DayOfWeek


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
        binding.calendar.apply {
            //처음 날짜를 월요일로 바꾸어주기
//            state()
//                .edit()
//                .setFirstDayOfWeek(DayOfWeek.of(Calendar.MONDAY))
//                .commit();
            // 월,일 style 수정
            setTitleFormatter(MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
            setWeekDayFormatter(ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
            setHeaderTextAppearance(R.style.CalendarWidgetHeader);
//            setOnDateChangedListener { widget, date, selected ->
//
//                val msg = "Selected date is " + date + "/" + (date.month + 1) + "/" + date.year
//                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
//
//            }
        }
        binding.gotodo.setOnClickListener {
            val intent = Intent(this,DayActivity::class.java)
            startActivity(intent)
        }

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