package com.example.sergey.courseproject.admin.workers.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.repositories.WorkerRepository
import kotlinx.android.synthetic.main.activity_worker_search.*

class WorkerSearchActivity : AppCompatActivity() {

    private val workersRepository by lazy {
        WorkerRepository(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_search)
        val manager = LinearLayoutManager(this)
        val adapter = SpecificWorkersRecyclerAdapter(this, workersRepository.getAllWorkers(null))

        specific_workers_recycler_view.layoutManager = manager
        specific_workers_recycler_view.adapter = adapter

        find_with_pattern_button.setOnClickListener {
            if(!TextUtils.isEmpty(pattern_edit_text.text.toString())) {
                adapter.swapData(workersRepository.getWorkersWithNamePattern(pattern_edit_text.text.toString()))
            } else {
                adapter.swapData(workersRepository.getAllWorkers(null))
            }
        }

    }
}
