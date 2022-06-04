package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
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
        val add_button = view.findViewById<AppCompatButton>(R.id.todo_add)
        val add_text = view.findViewById<EditText>(R.id.new_todo_edit)
        val dbHelper = DBHelper(context, "dysw.db", null, 1)
        val database = dbHelper.writableDatabase
        initData()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = TodoAdapter(data)
        adapter.itemClickListener = object : TodoAdapter.OnItemClickListener{
            override fun OnItemClick(data: Tododata, pos: Int) {
                // Toast.makeText(recyclerView.context,data.textString,Toast.LENGTH_SHORT).show()
                adapter.show_edit_delete_button(pos)
            }

            override fun onItemDelete(data: Tododata, pos: Int) {
                adapter.removeItem(pos)
            }

            override fun OnItemEdit(data: Tododata, pos: Int) {
                TODO("Not yet implemented")
            }
        }
        recyclerView.adapter = adapter

        // edittext에 적은 할일을 추가해주는 버튼
        add_button.setOnClickListener {
            val new_todo = add_text.text.toString()
            data.add(Tododata(new_todo,false))
            // Log.i(new_todo,"new_todo")
            // dbHelper.insertTodoData(new_todo.toString())
            add_text.clearFocus()
            add_text.text.clear()
        }

        val simpleItemTouchCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.moveItem(viewHolder.adapterPosition,target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return view
    }

    private fun initData() {
        // DB에 저장된 값들 읽어오기
        data.add(Tododata("씻기",false))
        data.add(Tododata("후하후하",false))
        data.add(Tododata("양치하기",false))
        data.add(Tododata("배고파",false))
        data.add(Tododata("뿌링클",false))
        data.add(Tododata("허니콤보",false))
        data.add(Tododata("돈냉",false))
        data.add(Tododata("루나코인",false))
    }
}