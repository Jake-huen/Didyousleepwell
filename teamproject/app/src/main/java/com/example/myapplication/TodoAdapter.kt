package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TodorowBinding

class TodoAdapter(val items:ArrayList<Tododata>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(data:Tododata,pos:Int)
    }
    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.todotextView)
        val checkbutton = itemView.findViewById<ImageView>(R.id.checkbutton)
        init{
            checkbutton.setOnClickListener {
                //text정보, view정보도 줄 생각
                itemClickListener?.OnItemClick(items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todorow,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].textString
    }

    override fun getItemCount(): Int {
        return items.size
    }
}