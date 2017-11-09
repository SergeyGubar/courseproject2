package com.example.sergey.courseproject

import android.widget.Spinner
import com.example.sergey.courseproject.entities.User

/**
 * Created by sergey on 11/9/17.
 */

interface RegisterActivityApi {

    fun getUserData() : User?

    fun getSpinner() : Spinner

    fun showSuccessToast()
}
