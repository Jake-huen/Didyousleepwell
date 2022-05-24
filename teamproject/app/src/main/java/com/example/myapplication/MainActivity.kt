//메인 화면
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
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