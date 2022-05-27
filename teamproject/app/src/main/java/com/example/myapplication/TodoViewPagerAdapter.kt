package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TodoViewPagerAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        //2개 만들거임
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->TodoFragment()
            1->AnalysisFragment()
            else->TodoFragment()
        }
    }
}