package com.example.sergey.courseproject.admin.buses

import android.content.Context
import android.widget.Toast
import com.example.sergey.courseproject.entities.Bus
import com.example.sergey.courseproject.entities.Worker
import com.example.sergey.courseproject.helpers.SQLiteHelper
import com.example.sergey.courseproject.repositories.BusesRepository
import com.example.sergey.courseproject.repositories.StationRepository
import com.example.sergey.courseproject.repositories.WorkerRepository

/**
 * Created by Sergey on 11/25/2017.
 */
class AddBusActivityPresenter(private val context: Context) {
    private val stationsRepository = StationRepository(context)
    private val workersRepository = WorkerRepository(context)
    private val busesRepository = BusesRepository(context)
    fun getStations() : ArrayList<CharSequence> = stationsRepository.stationsNames

    fun getDrivers() : List<Worker> = workersRepository.getWorkersWithRole("driver")

    fun addBus(bus : Bus) {
        if(busesRepository.addBus(bus) < 0)  {
            Toast.makeText(context, "Bus add failed!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Bus add success!", Toast.LENGTH_SHORT).show()
        }
    }
}