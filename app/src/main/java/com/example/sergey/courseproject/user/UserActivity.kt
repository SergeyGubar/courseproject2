package com.example.sergey.courseproject.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Ticket
import com.example.sergey.courseproject.repositories.BusesRepository
import com.example.sergey.courseproject.repositories.JourneyRepository
import com.example.sergey.courseproject.repositories.TicketRepository
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.activity_user.view.*

class UserActivity : AppCompatActivity() {

    val ticketRepository by lazy {
        TicketRepository(this)
    }

    val busesRepository by lazy {
        BusesRepository(this)
    }

    val journeysRepository by lazy {
        JourneyRepository(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // TODO : Initialize Journeys adapter

        select_journey_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Nope
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val journeyId = parent?.getItemAtPosition(position).toString().toInt()
                val journey = journeysRepository.getJourneyById(journeyId)
                journey_information_text_view.setText("ID : ${journey.id} Cost ${journey.cost} Date ${journey.date}")
            }

        }
        buy_ticket_button.setOnClickListener {
            val ticket = Ticket()
            ticket.apply {
                journeyId = select_journey_spinner.selectedItem.toString().toInt()
                val journey = journeysRepository.getJourneyById(journeyId)
                val bus = busesRepository.getBusById(journey.id)
                val numberOfSeats = bus.numberOfSeats
                setSeat(numberOfSeats)
            }

            if (ticketRepository.addTicket(ticket) < 0) {
                Toast.makeText(this, "ticket wasn't added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ticket bought!", Toast.LENGTH_SHORT).show()

            }


        }
    }
}
