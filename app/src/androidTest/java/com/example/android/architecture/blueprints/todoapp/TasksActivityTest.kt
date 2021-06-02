package com.example.android.architecture.blueprints.todoapp

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
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

}