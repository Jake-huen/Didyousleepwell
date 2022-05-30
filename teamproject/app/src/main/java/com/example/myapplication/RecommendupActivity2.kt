//잘 시간을 토대로 깰 시간을 추천(RecommendupActivity에서 넘어옴)
package com.example.myapplication

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityRecommendup2Binding
import com.example.myapplication.databinding.ActivityRecommendupBinding

class RecommendupActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityRecommendup2Binding
    lateinit var dbHelper: DBHelper
    lateinit var database: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRecommendup2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initlayout()
    }

    private fun initlayout() {

        dbHelper = DBHelper(this, "dysw.db", null, 1)
        val sleepTime = dbHelper.getTimeFallSleep()
        Log.d("sleeptime", sleepTime.toString())

        var hour = intent.getIntExtra("hour", 0)
        var minute = intent.getIntExtra("minute", 0) + sleepTime//평균 잠드는데 까지 걸리는 시간 더해주면 됨.

        while(minute>59){
            if(minute>59) {
                hour += 1
                minute -= 60
            }
            if(hour>23) {
                hour -= 24
            }
        }


        val timeString = hour.toString() + "시 " + minute.toString() + "분"

        binding.timeTextView1.text = timeString

        var hour2 = hour+1
        var minute2 = minute+30

        while(minute2>59){
            if(minute2>59) {
                hour2 += 1
                minute2 -= 60
            }
            if(hour2>23) {
                hour2 -= 24
            }
        }
        val timeString2 = hour2.toString() + "시" + minute2.toString() + "분"
        binding.timeTextView2.text = timeString2

        var hour3 = hour2+1
        var minute3 = minute2+30

        while(minute3>59){
            if(minute3>59) {
                hour3 += 1
                minute3 -= 60
            }
            if(hour3>23) {
                hour3 -= 24
            }
        }
        val timeString3 = hour3.toString() + "시" + minute3.toString() + "분"
        binding.timeTextView3.text = timeString3

        var hour4 = hour3+1
        var minute4 = minute3+30

        while(minute4>59){
            if(minute4>59) {
                hour4 += 1
                minute4 -= 60
            }
            if(hour4>23) {
                hour4 -= 24
            }
        }
        val timeString4 = hour4.toString() + "시" + minute4.toString() + "분"
        binding.timeTextView4.text = timeString4

        var hour5 = hour4+1
        var minute5 = minute4+30

        while(minute5>59){
            if(minute5>59) {
                hour5 += 1
                minute5 -= 60
            }
            if(hour5>23) {
                hour5 -= 24
            }
        }
        val timeString5 = hour5.toString() + "시" + minute5.toString() + "분"
        binding.timeTextView5.text = timeString5

    }


}