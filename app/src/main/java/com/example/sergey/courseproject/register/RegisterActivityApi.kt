package com.example.sergey.courseproject.register

import android.widget.Spinner
import com.example.sergey.courseproject.entities.Worker

/**
 * Created by sergey on 11/9/17.
 */

interface RegisterActivityApi {


    fun showSuccessToast()

    fun showFailMessage()

    fun cleanInputs()

    val rolesSpinner: Spinner

    val worker: Worker
    
    val stationsSpinner: Spinner

}
