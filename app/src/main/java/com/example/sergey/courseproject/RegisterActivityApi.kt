package com.example.sergey.courseproject

import android.widget.Spinner
import com.example.sergey.courseproject.entities.User

/**
 * Created by sergey on 11/9/17.
 */

interface RegisterActivityApi {


    fun showSuccessToast()

    fun showFailMessage()

    fun cleanInputs()

    val spinner: Spinner

    val user: User
}
