//잘 시간 정해주는 액티비티 -> 일어날 시간을 알고 있을 경우
package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import com.example.myapplication.databinding.ActivityRecommenddownBinding
import java.util.*

class RecommenddownActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecommenddownBinding
    lateinit var timePicker : TimePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommenddownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }


    private fun initLayout() {
        binding.button.setOnClickListener {
            var intent = Intent(this, RecommenddownActivity2::class.java)
            timePicker = binding.recommenddownTimePicker
            val hour = timePicker.hour
            val minute = timePicker.minute
            intent.putExtra("hour", hour)
            intent.putExtra("minute", minute)
            startActivity(intent)
        }

    }
}