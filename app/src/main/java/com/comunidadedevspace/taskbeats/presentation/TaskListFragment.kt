package com.comunidadedevspace.taskbeats.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    private lateinit var ctnContent: LinearLayout

    // adapter
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::openTaskListDetail)
    }
    // viewModel
    private val viewModel: TaskListViewModel by lazy {
        TaskListViewModel.create(requireActivity().application)
    }

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctnContent = view.findViewById(R.id.ctn_content)

        // RecyclerView
        val taskList: RecyclerView = view.findViewById(R.id.rv_task_list)
        taskList.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        lisFromDataBase()
    }

    private fun lisFromDataBase() { //inserir item list de tarefa da base de dados
        //Observer
        val listObserver = Observer<List<Task>> { listTasks ->//renomei o it
            if (listTasks.isEmpty()) {//imagem de estado vazio
                ctnContent.visibility = View.VISIBLE
            } else {
                ctnContent.visibility = View.GONE
            }
            adapter.submitList(listTasks)
        }

        //Live Data
        viewModel.taskListLiveData.observe(this, listObserver)
    }

        private fun openTaskListDetail(task: Task) {
        val intent = TaskDetailActivity.start(requireContext(), task)
        requireActivity().startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment TaskListFragment.
         */
        @JvmStatic
        fun newInstance() =
            TaskListFragment()
    }
}