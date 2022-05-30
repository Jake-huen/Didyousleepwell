package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TodorowBinding

class TodoAdapter (val items:ArrayList<Tododata>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){ // RecyclerView에 배치되는 View의 설정
        // itemView(LinearLayout)에 포함되어 있는 View들 중에서 찾아주는 작업
        val textView = itemView.findViewById<TextView>(R.id.todoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todorow,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].textString
    }

    override fun getItemCount(): Int {
        return items.size
    }
}