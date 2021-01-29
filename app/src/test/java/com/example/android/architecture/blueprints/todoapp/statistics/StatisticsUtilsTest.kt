package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    /*
    If there's no completed task and one active task,
    then there are 100% active tasks and 0% completed tasks.
     */

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsZeroHundred() {
        val tasks = listOf<Task>(
                Task("Title", "Description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }

    /*
    If there's 2 completed task and 3 active task,
    then there are 60% active tasks and 40% completed tasks.
     */

    @Test
    fun getActiveAndCompletedStats_both_returnFortySixty() {
        val tasks = listOf<Task>(
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        val tasks = emptyList<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        val tasks = null

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }


}