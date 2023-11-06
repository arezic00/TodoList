package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.todolist.databinding.ActivityAddBinding
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddToDb.setOnClickListener {
            lifecycleScope.launch {
                val title: String = binding.etAddTodo.text.toString()
                val todo = Todo(title = title)
                TodoDatabase.getInstance(this@AddActivity).dao.insertTodo(todo)
                finish()
            }
        }
    }
}