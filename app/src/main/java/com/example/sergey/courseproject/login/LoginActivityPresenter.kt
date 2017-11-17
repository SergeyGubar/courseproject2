package com.example.sergey.courseproject.login

import android.content.Context

import com.example.sergey.courseproject.repositories.WorkerRepository

/**
 * Created by sgubar on 11/17/17.
 */

class LoginActivityPresenter(private val mApi: LoginActivityApi, private val mCtx: Context) {
    private val mWorkerRepository: WorkerRepository

    init {
        mWorkerRepository = WorkerRepository(mCtx)
    }

    fun logIn() {}
}
