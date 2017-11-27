package com.example.sergey.courseproject.admin.buses

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.repositories.BusesRepository
import kotlinx.android.synthetic.main.activity_edit_bus.*

class EditBusActivity : AppCompatActivity() {

    val busRepository : BusesRepository by lazy {
        BusesRepository(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bus)
        val bus = busRepository.getBusById(intent.extras.getBundle(EXTRA_KEY).getInt(BUS_ID_EXTRA_KEY))
        bus_id_edit_text.setText(bus.id.toString())
        bus_driver_id_edit_text.setText(bus.driverId.toString())
        bus_number_of_seats_edit_text.setText(bus.numberOfSeats.toString())
        bus_station_edit_text.setText(bus.stationId.toString())
        bus_brand_edit_text.setText(bus.brand)

    }

    companion object {
        private val EXTRA_KEY = "extrabus"
        private val BUS_ID_EXTRA_KEY = "busid"
        fun makeIntent(busId: Int, context: Context): Intent {
            val extra = Bundle()
            extra.putInt(BUS_ID_EXTRA_KEY, busId)
            val intent = Intent(context, EditBusActivity::class.java)
            intent.putExtra(EXTRA_KEY, extra)
            return intent
        }
    }
}
