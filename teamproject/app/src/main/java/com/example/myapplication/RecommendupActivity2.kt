//잘 시간을 토대로 깰 시간을 추천(RecommendupActivity에서 넘어옴)
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityRecommendup2Binding
import com.example.myapplication.databinding.ActivityRecommendupBinding

class RecommendupActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityRecommendup2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRecommendup2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initlayout()
    }

    private fun initlayout() {

        val hour = intent.getIntExtra("hour", 0)
        val minute = intent.getIntExtra("minute", 0)
        val timeString = hour.toString() + "시 " + minute.toString() + "분"

        binding.timeTextView1.text = timeString

    }


}