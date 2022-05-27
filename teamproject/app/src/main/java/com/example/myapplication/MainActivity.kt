//메인 화면
package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dbHelper: DBHelper = DBHelper(this, "dysw.db", null, 1)
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
            setOnDateLongClickListener { widget, date ->
                val intent = Intent(this@MainActivity,DayActivity::class.java)
                startActivity(intent)
            }
        }

        //깰시간 정해주는 버튼
        binding.button1.setOnClickListener {
            val intent = Intent(this, DateActivity::class.java)
            startActivity(intent)
        }
        //잘시간 벙해주는 버튼
        binding.button2.setOnClickListener {
            val intent = Intent(this, RecommenddownActivity::class.java)
            startActivity(intent)
        }
    }
}