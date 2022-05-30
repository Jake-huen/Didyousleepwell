//일어날 시간 정해주는 액티비티 -> 잘 시간을 알고 있을 경우
package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import com.example.myapplication.databinding.ActivityRecommendupBinding

class RecommendupActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecommendupBinding
    lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiLayout()
    }

    private fun intiLayout() {
        binding.button.setOnClickListener {
            var intent = Intent(this, RecommendupActivity2::class.java)
            timePicker = binding.recommendupTimePicker
            val hour = timePicker.hour
            val minute = timePicker.minute
            intent.putExtra("hour", hour)
            intent.putExtra("minute", minute)
            startActivity(intent)
        }







    }
}