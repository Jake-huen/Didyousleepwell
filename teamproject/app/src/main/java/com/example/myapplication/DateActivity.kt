package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDateBinding
import com.google.android.material.tabs.TabLayoutMediator

class DateActivity : AppCompatActivity() {
    lateinit var binding: ActivityDateBinding
    val textarr = arrayListOf<String>("투두리스트","잠 분석")
    //val iconarr = arrayListOf<Int>(R.drawable.ic_android_black_24dp, R.drawable.ic_baseline_adb_24)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        binding.viewpager.adapter = TodoViewPagerAdapter(this)
        TabLayoutMediator(binding.tab, binding.viewpager){
                tab, position->
            tab.text = textarr[position]
            //tab.setIcon(iconarr[position])
        }.attach()
    }

}