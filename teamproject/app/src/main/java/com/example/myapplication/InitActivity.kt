package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityInitBinding

class InitActivity : AppCompatActivity() {
    lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isDB()

        initLayout()

    }

    private fun isDB(){

        val dbfile = getDatabasePath("dysw.db")
        if(dbfile.exists()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun delay(){
        Handler(Looper.getMainLooper()).postDelayed({
            //실행할 코드
        }, 300000)
    }

    private fun initLayout() {


        /*
        * InitFragment View
        * TODO -> 나중에 권한 부여 받게 되면 Fragment 추가할 것.
        * addFragment -> Fragment 추가작업
        * */
        val recommendAdapter = InitViewPagerAdapter(this)
        recommendAdapter.addFragment(InitFragment1())
        recommendAdapter.addFragment(InitFragment2())
        recommendAdapter.addFragment(InitFragment3())
        recommendAdapter.addFragment(InitFragment4())

        val viewpager: ViewPager2 = binding.viewPager
        binding.viewPager.adapter = recommendAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val child = binding.viewPager.getChildAt(0)
        (child as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER


    }

}