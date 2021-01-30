package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    /*
    If there's no completed task and one active task,
    then there are 100% active tasks and 0% completed tasks.
     */

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsZeroHundred() {
        // Given a list of tasks with a single, active, task
        val tasks = listOf<Task>(
                Task("Title", "Description", isCompleted = false)
        )
        // When the list of tasks is computed with a completed task
        val result = getActiveAndCompletedStats(tasks)

        // Then the percentages are 0 and 100
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    /*
    If there's 1 completed task and no active task,
    then there are 0% active tasks and 100% completed tasks.
     */
    @Test
    fun getActiveAndCompletedStats_noActive_returnsHundredZero() {
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = true)
        )
        // When the list of tasks is computed with a completed task
        val result = getActiveAndCompletedStats(tasks)

        // Then the percentages are 100 and 0
        assertEquals(100f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    /*
    If there's 2 completed task and 3 active task,
    then there are 60% active tasks and 40% completed tasks.
     */

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Given 2 completed tasks and 3 active tasks
        val tasks = listOf<Task>(
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false)
        )
        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)

        // Then the result is 40-60
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

    /*
    If there's 3 completed task and 2 active task,
    then there are 40% active tasks and 60% completed tasks.
     */

    @Test
    fun getActiveAndCompletedStats_both_returnSixtyForty() {
        // Given 3 completed tasks and 2 active tasks
        val tasks = listOf<Task>(
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false)
        )
        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)

        // Then the result is 60-40
        assertEquals(60f, result.completedTasksPercent)
        assertEquals(40f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        // When there are no tasks
        val result = getActiveAndCompletedStats(emptyList())

        // Both active and completed tasks are 0
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        // When there's an error loading stats
        val result = getActiveAndCompletedStats(null)

        // Both active and completed tasks are 0
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

}