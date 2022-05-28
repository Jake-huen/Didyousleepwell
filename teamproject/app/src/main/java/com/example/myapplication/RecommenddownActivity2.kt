//깰 시간을 토대로 잘 시간을 추천(RecommenddownActivity에서 넘어옴)
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityRecommenddown2Binding

class RecommenddownActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityRecommenddown2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommenddown2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        //나이에 따라서 깰시간 추천해주면 된다.
        var hour = intent.getIntExtra("hour", 0)
        var minute = intent.getIntExtra("minute", 0) + 17//평균 잠드는데 까지 걸리는 시간 더해주면 됨.

        if(minute>59) {
            hour += 1
            minute -= 60
        }
        if(hour>23) {
            hour -= 24
        }

        val timeString = hour.toString() + "시 " + minute.toString() + "분"

        binding.timeTextView1.text = timeString

        var hour2 = hour+1
        var minute2 = minute+30
        if(minute2>59) {
            hour2 += 1
            minute2 -= 60
        }
        if(hour2>23) {
            hour2 -= 24
        }
        val timeString2 = hour2.toString() + "시" + minute2.toString() + "분"
        binding.timeTextView2.text = timeString2

        var hour3 = hour2+1
        var minute3 = minute2+30
        if(minute3>59) {
            hour3 += 1
            minute3 -= 60
        }
        if(hour3>23) {
            hour3 -= 24
        }
        val timeString3 = hour3.toString() + "시" + minute3.toString() + "분"
        binding.timeTextView3.text = timeString3

        var hour4 = hour3+1
        var minute4 = minute3+30
        if(minute4>59) {
            hour4 += 1
            minute4 -= 60
        }
        if(hour4>23) {
            hour4 -= 24
        }
        val timeString4 = hour4.toString() + "시" + minute4.toString() + "분"
        binding.timeTextView4.text = timeString4

        var hour5 = hour4+1
        var minute5 = minute4+30
        if(minute5>59) {
            hour5 += 1
            minute5 -= 60
        }
        if(hour5>23) {
            hour5 -= 24
        }
        val timeString5 = hour5.toString() + "시" + minute5.toString() + "분"
        binding.timeTextView5.text = timeString5
    }
}