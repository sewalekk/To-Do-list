package com.sevvalekinci.todolist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var linearLayoutTasks: LinearLayout
    private lateinit var buttonAdd: Button

    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        linearLayoutTasks = findViewById(R.id.linearlayoutTasks)
        buttonAdd = findViewById(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        val taskName = editTextTask.text.toString()
        val task = Task(taskName)
        tasks.add(task)
        displayTasks()
        editTextTask.text.clear()
    }

    private fun displayTasks() {
        linearLayoutTasks.removeAllViews()
        for (task in tasks) {
            val taskView = layoutInflater.inflate(R.layout.item_task, null)
            val textViewTaskName = taskView.findViewById<TextView>(R.id.textViewTaskName)
            val buttonDelete = taskView.findViewById<Button>(R.id.buttonDelete)
            val checkBoxTask = taskView.findViewById<CheckBox>(R.id.checkBoxTask)

            textViewTaskName.text = task.taskName
            checkBoxTask.isChecked = task.isChecked

            buttonDelete.setOnClickListener {
                deleteTask(task)
            }

            checkBoxTask.setOnCheckedChangeListener { _, isChecked ->
                task.isChecked = isChecked
            }

            linearLayoutTasks.addView(taskView)
        }
    }

    private fun deleteTask(task: Task) {
        tasks.remove(task)
        displayTasks()
    }
}
