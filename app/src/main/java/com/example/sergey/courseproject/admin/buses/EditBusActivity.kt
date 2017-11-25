package com.example.sergey.courseproject.admin.buses

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sergey.courseproject.R

class EditBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bus)
    }

    companion object {
        val EXTRA_KEY = "extrabus"
        val BUSID_EXTRA_KEY = "busid"
        fun makeIntent(busId : Int) : Intent {
            val extra = Bundle()
            extra.putInt(BUSID_EXTRA_KEY, busId)
            val intent = Intent()
            intent.putExtra(EXTRA_KEY, extra)
            return intent
        }
    }
}
