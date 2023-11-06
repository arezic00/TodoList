package com.example.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("SELECT * FROM todo ORDER BY isChecked")
    suspend fun getAllTodos() : List<Todo>

    @Query("DELETE FROM todo WHERE isChecked")
    suspend fun deleteAllCheckedTodos()

    @Delete
    suspend fun deleteTodo(todo: Todo)
}