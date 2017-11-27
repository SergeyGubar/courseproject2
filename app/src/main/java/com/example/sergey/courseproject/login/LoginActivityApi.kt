package com.example.sergey.courseproject.login

import com.example.sergey.courseproject.entities.Worker

/**
 * Created by sgubar on 11/17/17.
 */

interface LoginActivityApi {
    val worker : Worker
    fun showToast(message: String)

}
