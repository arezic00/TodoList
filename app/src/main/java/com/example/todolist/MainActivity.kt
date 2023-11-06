package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewEvent {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            lifecycleScope.launch {
                TodoDatabase.getInstance(this@MainActivity).dao.deleteAllCheckedTodos()
                setRecyclerViewData()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewData()
    }

    override fun onItemClick(position: Int) {
        lifecycleScope.launch {
            val todo = todoAdapter.todoList[position]
            todo.isChecked = !todo.isChecked
            TodoDatabase.getInstance(this@MainActivity).dao.insertTodo(todo)
        }
    }

    private fun setRecyclerViewData() {
        lifecycleScope.launch {
            val list = TodoDatabase.getInstance(this@MainActivity).dao.getAllTodos()
            todoAdapter = TodoAdapter(this@MainActivity).apply {
                setData(list)
            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = todoAdapter
            }
        }
    }


}