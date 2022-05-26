package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityDayBinding

class DayActivity : AppCompatActivity() {
    var data:ArrayList<Tododata> = ArrayList()
    lateinit var binding: ActivityDayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initRecyclerView()
    }

    private fun initData() {
        data.add(Tododata("씻고 준비하기"))
        data.add(Tododata("다시 잠들기"))
        data.add(Tododata("코딩하기"))
        data.add(Tododata("데이트하기"))
    }

    private fun initRecyclerView() {
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.todoRecyclerView.adapter = TodoAdapter(data)
    }
}