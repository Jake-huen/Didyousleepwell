package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        adapter.itemClickListener = object : TodoAdapter.OnItemClickListener{
            override fun OnItemClick(data: Tododata, pos: Int) {
                Toast.makeText(recyclerView.context,data.textString,Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // activity에 정보 전달해준다.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 정보 수신 fragment 간에는 통신 어려우니까 activity를 통해서 통신
//        if(activity!= null){
//            val intent = activity?.intent
//            if(intent!=null){
//                val str = intent.getStringExtra("name")
//            }
//        }
    }

    private fun initData() {
        // DB에 저장된 값들 읽어오기
        data.add(Tododata("씻기"))
        data.add(Tododata("후하후하"))
        data.add(Tododata("양치하기"))
        data.add(Tododata("배고파"))
        data.add(Tododata("뿌링클"))
        data.add(Tododata("허니콤보"))
        data.add(Tododata("돈냉"))
        data.add(Tododata("루나코인"))
    }
}