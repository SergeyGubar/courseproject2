package com.example.sergey.courseproject.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Ticket
import com.example.sergey.courseproject.repositories.BusesRepository
import com.example.sergey.courseproject.repositories.JourneyRepository
import com.example.sergey.courseproject.repositories.RoutesRepository
import com.example.sergey.courseproject.repositories.TicketRepository
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private val ticketRepository by lazy {
        TicketRepository(this)
    }

    private val busesRepository by lazy {
        BusesRepository(this)
    }

    private val journeysRepository by lazy {
        JourneyRepository(this)
    }

    private val routesRepository by lazy {
        RoutesRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val listOfIds = ArrayList<CharSequence>()
        val journeys = journeysRepository.getAllJourneys(null)
        journeys.forEach {
            listOfIds.add(it.id.toString())
        }

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfIds)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        select_journey_spinner.adapter = spinnerAdapter

        select_journey_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Nope
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val journeyId = parent?.getItemAtPosition(position).toString().toInt()
                val journey = journeysRepository.getJourneyById(journeyId)
                journey_information_text_view.setText("ID : ${journey.id} Cost: ${journey.cost} Date: ${journey.date}")
                val routeNumber = journey.route
                val route = routesRepository.getRouteByNumber(routeNumber)
                route_information_text_view.setText("Number ${route.number} Start Station: ${route.startStationId} End: ${route.endStationId}")
            }

        }
        buy_ticket_button.setOnClickListener {
            val ticket = Ticket()
            ticket.apply {
                journeyId = select_journey_spinner.selectedItem.toString().toInt()
                val journey = journeysRepository.getJourneyById(journeyId)
                val bus = busesRepository.getBusById(journey.busId)
                val numberOfSeats = bus.numberOfSeats
                setSeat(numberOfSeats)
            }
            val ticketId = ticketRepository.addTicket(ticket)
            if (ticketId < 0) {
                Toast.makeText(this, "ticket wasn't added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ticket bought!", Toast.LENGTH_SHORT).show()
                startActivity(TicketActivity.makeIntent(this, ticketId))
            }


        }
    }
}
