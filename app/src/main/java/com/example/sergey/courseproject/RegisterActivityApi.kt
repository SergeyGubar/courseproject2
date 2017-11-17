package com.example.sergey.courseproject

import android.widget.Spinner
import com.example.sergey.courseproject.entities.Worker

/**
 * Created by sergey on 11/9/17.
 */

interface RegisterActivityApi {


    fun showSuccessToast()

    fun showFailMessage()

    fun cleanInputs()

    val spinner: Spinner

    val worker: Worker

}
