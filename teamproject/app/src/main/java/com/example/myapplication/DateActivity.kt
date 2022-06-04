package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDateBinding
import com.google.android.material.tabs.TabLayoutMediator

class DateActivity : AppCompatActivity() {
    lateinit var binding: ActivityDateBinding
    val textarr = arrayListOf<String>("투두리스트","잠 분석")
    val iconarr = arrayListOf<Int>(R.drawable.ic_baseline_today_24, R.drawable.ic_baseline_analytics_24)
    lateinit var datea: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()

    }

    fun setDataAtFragment(fragment: TodoFragment, date:String): TodoFragment {
        val bundle = Bundle()
        bundle.putString("date", date)
        fragment.arguments = bundle

        return fragment
    }


    private fun initLayout() {

        val date = intent.getStringExtra("날짜")
        datea = date.toString();

        binding.datetext.text = date

        binding.viewpager.adapter = TodoViewPagerAdapter(this)
        TabLayoutMediator(binding.tab, binding.viewpager){
                tab, position->
            tab.text = textarr[position]
            tab.setIcon(iconarr[position])
        }.attach()

    }

    fun getDate() : String{
        return datea
    }

}