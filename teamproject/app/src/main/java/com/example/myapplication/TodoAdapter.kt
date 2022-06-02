package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(val items:ArrayList<Tododata>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(data:Tododata,pos:Int)
    }
    var itemClickListener:OnItemClickListener?=null

    fun moveItem(oldPos:Int, newPos:Int){
        val item = items[oldPos]
        items.removeAt(oldPos)
        items.add(newPos,item)
        notifyItemMoved(oldPos,newPos)
    }

    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun show_edit_delete_button(pos:Int){
        items[pos].show_edit_delete=!items[pos].show_edit_delete
        notifyItemChanged(pos)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.todo_textView)
        val checkbutton = itemView.findViewById<ImageView>(R.id.todo_checkbutton)
        val edit_button = itemView.findViewById<Button>(R.id.todo_edit)
        val delete_button = itemView.findViewById<Button>(R.id.todo_delete)
        init{
            checkbutton.setOnClickListener {
                //text정보, view정보도 줄 생각
                itemClickListener?.OnItemClick(items[adapterPosition],adapterPosition)
            }
            edit_button.setOnClickListener {

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
        if(items[position].show_edit_delete){
            holder.edit_button.visibility = View.VISIBLE
            holder.delete_button.visibility = View.VISIBLE
        }else{
            holder.edit_button.visibility = View.GONE
            holder.delete_button.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}