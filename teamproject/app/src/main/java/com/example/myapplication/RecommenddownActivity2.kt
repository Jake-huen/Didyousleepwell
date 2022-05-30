//깰 시간을 토대로 잘 시간을 추천(RecommenddownActivity에서 넘어옴)
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityRecommenddown2Binding

class RecommenddownActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityRecommenddown2Binding
    private var dbHelper = DBHelper(this, "dysw.db", null, 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommenddown2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {

        val age = dbHelper.getAge()
        //어짜피 한시간 반 고정이라 나이값은 딱히 필요없을듯..
        var sleepTime = dbHelper.getTimeFallSleep()

        var hour = intent.getIntExtra("hour", 0)
        var minute = intent.getIntExtra("minute", 0)

        hour -= 1
        minute -= 30

        while(minute<0){
            if(minute<0) {
                hour -= 1
                minute += 60
            }
            if(hour<0) {
                hour += 24
            }
        }

        minute += sleepTime

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

        binding.timeTextView5.text = timeString //마지막 줄 부터 출력

        var hour2 = hour-1
        var minute2 = minute-30

        while(minute2<0){
            if(minute2<0) {
                hour2 -= 1
                minute2 += 60
            }
            if(hour2<0) {
                hour2 += 24
            }
        }

        val timeString2 = hour2.toString() + "시" + minute2.toString() + "분"
        binding.timeTextView4.text = timeString2

        var hour3 = hour2-1
        var minute3 = minute2-30

        while(minute3<0){
            if(minute3<0) {
                hour3 -= 1
                minute3 += 60
            }
            if(hour3<0) {
                hour3 += 24
            }
        }
        val timeString3 = hour3.toString() + "시" + minute3.toString() + "분"
        binding.timeTextView3.text = timeString3

        var hour4 = hour3-1
        var minute4 = minute3-30

        while(minute4<0){
            if(minute4<0) {
                hour4 -= 1
                minute4 += 60
            }
            if(hour4<0) {
                hour4 += 24
            }
        }
        val timeString4 = hour4.toString() + "시" + minute4.toString() + "분"
        binding.timeTextView2.text = timeString4

        var hour5 = hour4-1
        var minute5 = minute4-30

        while(minute5<0){
            if(minute5<0) {
                hour5 -= 1
                minute5 += 60
            }
            if(hour5<0) {
                hour5 += 24
            }
        }
        val timeString5 = hour5.toString() + "시" + minute5.toString() + "분"
        binding.timeTextView1.text = timeString5
    }
}