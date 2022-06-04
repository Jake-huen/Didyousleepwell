package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.databinding.ActivityDateBinding
import com.google.android.material.tabs.TabLayoutMediator

class DateActivity : AppCompatActivity() {
    lateinit var binding: ActivityDateBinding
    val textarr = arrayListOf<String>("투두리스트","잠 분석")
    val iconarr = arrayListOf<Int>(R.drawable.ic_baseline_today_24, R.drawable.ic_baseline_analytics_24)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()

    }

    private fun initLayout() {
        val secondIntent = intent
        val date = secondIntent.getStringExtra("날짜")
        binding.viewpager.adapter = TodoViewPagerAdapter(this)
        TabLayoutMediator(binding.tab, binding.viewpager){
                tab, position->
            tab.text = textarr[position]
            tab.setIcon(iconarr[position])
        }.attach()
    }

}