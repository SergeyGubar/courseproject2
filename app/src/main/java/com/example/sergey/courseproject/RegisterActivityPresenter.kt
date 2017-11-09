package com.example.sergey.courseproject

import android.content.Context
import android.widget.ArrayAdapter

/**
 * Created by sergey on 11/9/17.
 */

class RegisterActivityPresenter(private val mCtx: Context, private val mApi: RegisterActivityApi) {

    fun addUser() {

    }

    fun initializeSpinnerData() {
        val spinner = mApi.getSpinner()
        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter
                .createFromResource(mCtx,
                        R.array.roles_items, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
    }
}
