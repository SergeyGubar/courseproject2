package com.example.sergey.courseproject.station

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Station
import kotlinx.android.synthetic.main.activity_add_station.*

class AddStationActivity : AppCompatActivity() {
    private lateinit var mPresenter : StationActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_station)
        mPresenter = StationActivityPresenter(this)
        station_add_station_add_button.setOnClickListener {
            mPresenter.addStation(Station(station_add_name_edit_text.text.toString(),
                    station_add_city_edit_text.text.toString())
                    )
        }
    }
}
