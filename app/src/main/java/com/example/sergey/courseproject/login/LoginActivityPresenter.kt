package com.example.sergey.courseproject.login

import android.content.Context
import android.content.Intent
import com.example.sergey.courseproject.admin.AdminActivity
import com.example.sergey.courseproject.cashier.CashierActivity
import com.example.sergey.courseproject.repositories.WorkerRepository
import com.example.sergey.courseproject.user.UserActivity

/**
 * Created by sgubar on 11/17/17.
 */

class LoginActivityPresenter(private val mApi: LoginActivityApi, private val mCtx: Context) {
    private val mWorkerRepository: WorkerRepository = WorkerRepository(mCtx)

    fun logIn() {
        val role: String = mWorkerRepository.getWorkerRole(mApi.worker)

        when (role.toLowerCase()) {
            "admin" -> mCtx.startActivity(Intent(mCtx, AdminActivity::class.java))
            "driver" -> mApi.showToast("There is no way for drivers")
            "user" -> mCtx.startActivity(Intent(mCtx, UserActivity::class.java))
            "cashier" -> mCtx.startActivity(Intent(mCtx, CashierActivity::class.java))
            else -> mApi.showToast("no such user")
        }

    }




}
