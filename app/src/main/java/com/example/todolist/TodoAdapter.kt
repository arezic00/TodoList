package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter(val listener: RecyclerViewEvent) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    val todoList = mutableListOf<Todo>()
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cbTodo.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.tvTodo.text = todoList[position].title
        holder.binding.cbTodo.isChecked = todoList[position].isChecked
    }

    fun setData(list: List<Todo>) {
        todoList.apply {
            clear()
            addAll(list)
        }
    }
}