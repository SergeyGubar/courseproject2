package com.example.sergey.courseproject.cashier

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Journey
import com.example.sergey.courseproject.repositories.BusesRepository
import com.example.sergey.courseproject.repositories.JourneyRepository
import com.example.sergey.courseproject.repositories.RoutesRepository
import kotlinx.android.synthetic.main.activity_journey_add.*

class JourneyAddActivity : AppCompatActivity() {

    private val busRepository by lazy {
        BusesRepository(this)
    }
    private val routesRepository by lazy {
        RoutesRepository(this)
    }

    private val journeyRepository by lazy {
        JourneyRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey_add)
        initAdapters()
        add_journey_button.setOnClickListener {
            val journey  = Journey()
            journey.busId = journey_bus_spinner.selectedItem.toString().toInt()
            journey.route = journey_route_number_spinner.selectedItem.toString().toInt()
            journey.date = journey_date_edit_text.text.toString()
            journey.cost = journey_cost_edit_text.text.toString().toInt()
            val id = journeyRepository.addJourney(journey)
            Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapters() {
        val listOfBuses = busRepository.getAllBusesId(null)
        val listOfRoutes = routesRepository.getAllRoutesNumbers(null)

        val routesAdapter = ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                listOfRoutes)
        val busesAdapter = ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                listOfBuses)


        routesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        busesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        journey_route_number_spinner.adapter = routesAdapter
        journey_bus_spinner.adapter = busesAdapter
    }
}
