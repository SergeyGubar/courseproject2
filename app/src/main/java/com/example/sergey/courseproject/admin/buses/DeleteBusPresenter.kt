package com.example.sergey.courseproject.admin.buses

import android.content.Context
import android.widget.Toast
import com.example.sergey.courseproject.entities.Bus
import com.example.sergey.courseproject.repositories.BusesRepository

/**
 * Created by Sergey on 11/26/2017.
 */
class DeleteBusPresenter(private val context: Context) {

    val busRepository: BusesRepository = BusesRepository(context)

    fun deleteBus(bus: Bus) {

        if (busRepository.deleteBus(bus)) {
            Toast.makeText(context, "Bus deleted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Bus delete failed", Toast.LENGTH_SHORT).show()
        }
    }
}