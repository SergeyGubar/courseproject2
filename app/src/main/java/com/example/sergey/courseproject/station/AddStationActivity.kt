package com.example.sergey.courseproject.station

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Station
import kotlinx.android.synthetic.main.activity_add_station.*

class AddStationActivity : AppCompatActivity(), AddStationActivityApi {
    private lateinit var mPresenter: AddStationActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_station)
        mPresenter = AddStationActivityPresenter(this, this)
        station_add_station_add_button.setOnClickListener {
            mPresenter.addStation(Station(station_add_name_edit_text.text.toString(),
                    station_add_city_edit_text.text.toString())
            )
        }

    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun clearInputs() {
        station_add_city_edit_text.setText("")
        station_add_name_edit_text.setText("")
    }


}
