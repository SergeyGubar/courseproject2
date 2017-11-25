package com.example.sergey.courseproject.admin.buses

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.widget.RelativeLayout

import com.example.sergey.courseproject.R
import kotlinx.android.synthetic.main.buses_recycler_view.*


/**
 * Created by sgubar on 11/20/17.
 */

class BusesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buses_recycler_view)

        add_floating_action_button.setOnClickListener {
            startActivity(Intent(this@BusesActivity, AddBusActivity::class.java))
        }

        init()
    }

    private fun init() {
        val adapter = BusesRecyclerAdapter(this)
        val manager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = manager
    }

    override fun onResume() {
        super.onResume()
        init()
    }

}
