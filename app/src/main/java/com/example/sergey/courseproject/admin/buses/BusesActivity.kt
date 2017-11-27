package com.example.sergey.courseproject.admin.buses

import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import android.widget.Toast

import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Bus
import kotlinx.android.synthetic.main.buses_recycler_view.*


/**
 * Created by sgubar on 11/20/17.
 */

class BusesActivity : AppCompatActivity(), DeleteBusCallback, EditBusCallback {

    private val presenter : DeleteBusPresenter by lazy {
        DeleteBusPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buses_recycler_view)

        add_floating_action_button.setOnClickListener {
            startActivity(Intent(this@BusesActivity, AddBusActivity::class.java))
        }

        init()
    }

    private fun init() {
        val adapter = BusesRecyclerAdapter(this, mDeleteCallback = this, mEditCallback = this)
        val manager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = manager
    }

    override fun deleteBus(bus: Bus) {
        presenter.deleteBus(bus)
        init()
    }

    override fun editBus(bus: Bus) {
        val intent = EditBusActivity.makeIntent(bus.id, this)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        init()
    }

}
