package com.sevvalekinci.todolist

data class Task(val taskName: String, var isChecked: Boolean = false) {
    fun toggleChecked() {
        isChecked = !isChecked
    }
}
