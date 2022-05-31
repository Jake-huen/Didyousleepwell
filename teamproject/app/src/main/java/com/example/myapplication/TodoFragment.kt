package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoFragment : Fragment() {
    private var data:ArrayList<Tododata> = ArrayList()
    lateinit var adapter:TodoAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todo,container,false)
        recyclerView = view.findViewById(R.id.todoRecyclerView)
        initData()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = TodoAdapter(data)
        recyclerView.adapter = adapter

        return view
    }

    private fun initData() {
        data.add(Tododata("씻기"))
        data.add(Tododata("데이트하기"))
        data.add(Tododata("양치하기"))
        data.add(Tododata("양치하기"))
        data.add(Tododata("양치하기"))
        data.add(Tododata("양치하기"))
        data.add(Tododata("양치하기"))
        data.add(Tododata("양치하기"))
    }
}