package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.TaskDAO
import com.comunidadedevspace.taskbeats.presentation.TaskListViewModel
import org.mockito.kotlin.mock

class TaskListViewModelTest {

    private val taskDao: TaskDAO = mock()

    private val underTest: TaskListViewModel by lazy {
        TaskListViewModel(taskDao)
    }





}