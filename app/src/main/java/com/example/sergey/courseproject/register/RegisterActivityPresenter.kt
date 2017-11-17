package com.example.sergey.courseproject

import android.content.Context
import android.widget.ArrayAdapter
import com.example.sergey.courseproject.repositories.WorkerRepository

/**
 * Created by sergey on 11/9/17.
 */

class RegisterActivityPresenter(private val mCtx: Context, private val mApi: RegisterActivityApi,
                                private val mWorkerRepository : WorkerRepository = WorkerRepository(mCtx)) {

    fun addUser() {
        val user = mApi.worker
        val userId = mWorkerRepository.addWorker(user)
        if(userId < 0) {
            mApi.showFailMessage()
        } else {
            mApi.showSuccessToast()
        }
        mApi.cleanInputs()
    }

    fun initializeSpinnerData() {
        val spinner = mApi.spinner
        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter
                .createFromResource(mCtx,
                        R.array.roles_items, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}
