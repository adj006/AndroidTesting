package com.example.android.architecture.blueprints.todoapp

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TasksActivityTest {

    private lateinit var repository: TasksRepository

    @Before
    fun initRepo() {
        repository = ServiceLocator.provideTasksRepository(getApplicationContext())
        runBlocking {
            repository.deleteAllTasks()
        }
    }

    @After
    fun resetRepo() {
        ServiceLocator.resetRepository()
    }

    @Test
    fun editTask() = runBlocking {
        // Set initial state.
        repository.saveTask(Task("T1", "Task 1"))

        // Start up Tasks screen.
        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)

        // Click on the task on the list and verify that all the data is correct.
        onView(withText("T1")).perform(click())
        onView(withId(R.id.task_detail_title_text)).check(matches(withText("T1")))
        onView(withId(R.id.task_detail_description_text)).check(matches(withText("Task 1")))
        onView(withId(R.id.task_detail_complete_checkbox)).check(matches(isNotChecked()))

        // Click on the edit button, edit, save.
        onView(withId(R.id.edit_task_fab)).perform(click())
        onView(withId(R.id.add_task_title_edit_text)).perform(replaceText("T2"))
        onView(withId(R.id.add_task_description_edit_text))
            .perform((replaceText("Task 2")))
        onView(withId(R.id.save_task_fab)).perform(click())

        // Verify task is displayed on screen in the task list.
        onView(withText("T2")).check(matches(isDisplayed()))

        // Verify previous task is not displayed.
        onView(withText("T1")).check(doesNotExist())

        // Make sure the activity is closed before resetting the db.
        activityScenario.close()
    }
}