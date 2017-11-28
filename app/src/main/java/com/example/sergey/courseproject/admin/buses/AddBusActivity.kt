package com.example.sergey.courseproject.admin.buses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Bus
import kotlinx.android.synthetic.main.activity_add_bus.*

class AddBusActivity : AppCompatActivity() {

    val presenter: AddBusActivityPresenter by lazy {
        AddBusActivityPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bus)
        val driversList = ArrayList<CharSequence>()

        presenter.getDrivers().forEach( {
                driversList.add(it.id.toString())
            }
        )
        val stationsList = presenter.getStations()


        val driversAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, driversList)
        val stationsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stationsList)

        driversAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        station_id_spinner.adapter = stationsAdapter
        driver_id_spinner.adapter = driversAdapter

        add_bus_button.setOnClickListener( { presenter.addBus(Bus(0,
                station_id_spinner.selectedItem.toString().toInt(),
                driver_id_spinner.selectedItem.toString().toInt(),
                seats_number_edit_text.text.toString().toInt(),
                brand_edit_text.text.toString()))})
    }

}
