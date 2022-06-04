//메인 화면
package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import org.threeten.bp.DayOfWeek

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
            state()
                .edit()
                .setFirstDayOfWeek(DayOfWeek.MONDAY)
                .commit();

            // 월,일 style 수정
            setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
            setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))
            setHeaderTextAppearance(R.style.CalendarWidgetHeader);

            // 해당 날짜 길게 누르면 TODO_LIST로 넘어갈 수 있음
            setOnDateLongClickListener { widget, date ->
                val intent = Intent(this@MainActivity, DateActivity::class.java)

                // 달력 format에 맞게 달 str 변환
                val month = date.month.toString().toInt()
                var month_str : String = ""
                if(month < 10){
                    month_str = "0$month"
                }
                else month_str = date.month.toString()

                // 달력 format에 맞게 일 str 변환
                val day = date.day.toString().toInt()
                var day_str : String = ""
                if(day < 10){
                    day_str = "0$day"
                }
                else day_str = date.day.toString()

                val calenderDate : String =  date.year.toString() + "-" + month_str + "-" + day_str
                intent.putExtra("날짜", calenderDate)
                startActivity(intent)
            }
            addDecorator(TodayDecorator())
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