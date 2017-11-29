package com.example.sergey.courseproject.cashier

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.sergey.courseproject.R
import kotlinx.android.synthetic.main.activity_cashier.*

class CashierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashier)

        journeys_button.setOnClickListener {
            startActivity(Intent(this@CashierActivity, JourneyAddActivity::class.java))
        }

        routes_button.setOnClickListener {
            startActivity(Intent(this@CashierActivity, RouteAddActivity::class.java))
        }

    }
}
