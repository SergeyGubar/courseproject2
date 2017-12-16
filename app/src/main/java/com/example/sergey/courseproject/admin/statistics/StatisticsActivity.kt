package com.example.sergey.courseproject.admin.statistics

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sergey.courseproject.R
import kotlinx.android.synthetic.main.activity_statistics.*

class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        experience_statistics.setOnClickListener {
            startActivity(Intent(this, ExperienceStatistics::class.java))
        }
    }
}
