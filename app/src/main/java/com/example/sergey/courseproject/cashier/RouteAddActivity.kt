package com.example.sergey.courseproject.cashier

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Route
import com.example.sergey.courseproject.repositories.RoutesRepository
import com.example.sergey.courseproject.repositories.StationRepository
import kotlinx.android.synthetic.main.activity_route_add.*

class RouteAddActivity : AppCompatActivity() {

    private val stationRepository by lazy {
        StationRepository(this)
    }

    private val routesRepository by lazy {
        RoutesRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_add)

        route_add_button.setOnClickListener {
            val route = Route()
            with(route) {
                number = route_number_edit_text.text.toString().toInt()
                startStationId = start_station_spinner.selectedItem.toString().toInt()
                endStationId = end_station_spinner.selectedItem.toString().toInt()
            }

            if(route.startStationId != route.endStationId) {
                routesRepository.add(route)
            } else {
                Toast.makeText(this, "Start station == end", Toast.LENGTH_SHORT).show()
            }
        }
        initAdapters()
    }

    private fun initAdapters() {
        val stationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stationRepository.stationIds)
        stationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        start_station_spinner.adapter = stationAdapter
        end_station_spinner.adapter = stationAdapter

    }
}
