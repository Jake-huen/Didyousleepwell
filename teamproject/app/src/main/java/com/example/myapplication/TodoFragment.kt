package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TodoFragment : Fragment() {
    private var dataList:ArrayList<Tododata> = ArrayList()
    lateinit var adapter:TodoAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var date: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 캘린더에서 데이트 값 가져옴      ex) date 값은 2022-06-01
        date = (context as DateActivity).getDate()
        if(date == "null"){
//            date = LocalDate.now().toS
            var now = LocalDate.now()
            date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }

    }

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
        adapter = TodoAdapter(dataList)
        adapter.itemClickListener = object : TodoAdapter.OnItemClickListener{
            override fun OnItemClick(data: Tododata, pos: Int) {
                // Toast.makeText(recyclerView.context,data.textString,Toast.LENGTH_SHORT).show()
                adapter.show_edit_delete_button(pos)
            }

            override fun onItemDelete(data: Tododata, pos: Int) {
                dbHelper.deleteTodo(dataList[pos].id.toString())
                adapter.removeItem(pos)

            }

            override fun OnItemEdit(data: Tododata, pos: Int) {

                var builder = AlertDialog.Builder(activity)
                builder.setTitle("Todo를 수정합니다.")
                builder.setIcon(R.drawable.icon)

                var v1 = layoutInflater.inflate(R.layout.dialog, null)
                builder.setView(v1)
                // p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용

                var listener = DialogInterface.OnClickListener { p0, p1 ->
                    var alert = p0 as AlertDialog
                    var edit1: EditText? = alert.findViewById<EditText>(R.id.editTextDialog)

                    val text = "${edit1?.text}"

                    dbHelper.updateTodo(dataList[pos].id, text)
                    initData()
                    adapter.notifyItemChanged(pos)
                }

                builder.setPositiveButton("확인", listener)
                builder.setNegativeButton("취소", null)
                builder.show()

            }
        }
        recyclerView.adapter = adapter

        // edittext에 적은 할일을 추가해주는 버튼
        add_button.setOnClickListener {
            val new_todo = add_text.text.toString()
            dbHelper.insertTodoData(date, Tododata(0, new_todo,false))
            initData()
            add_text.clearFocus()
            add_text.text.clear()
            adapter.notifyItemChange()
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
        val dbHelper = DBHelper(context, "dysw.db", null, 1)
        // TodoList
        dataList.clear()
        val list = dbHelper.selectTodo(date)
        for(i in list.indices){
            dataList.add(Tododata(list[i].id, list[i].textString, false))
        }

    }
}