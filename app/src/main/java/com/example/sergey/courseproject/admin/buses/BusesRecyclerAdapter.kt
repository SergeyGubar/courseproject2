package com.example.sergey.courseproject.admin.buses

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Bus
import com.example.sergey.courseproject.repositories.BusesRepository

/**
 * Created by Sergey on 11/25/2017.
 */
class BusesRecyclerAdapter(private val mCtx: Context,
                           private val mInflater: LayoutInflater = LayoutInflater.from(mCtx)) : RecyclerView.Adapter<BusesRecyclerAdapter.BusViewHolder>() {


    private val repository: BusesRepository
    private val data: List<Bus>

    init {
        repository = BusesRepository(mCtx)
        data = repository.allBuses
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        val view = mInflater.inflate(R.layout.bus_item, parent, false)
        return BusViewHolder(view)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: BusViewHolder, position: Int) = holder.bind(data[position])

    inner class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val busStationTextView : TextView = itemView.findViewById(R.id.bus_station_text_view)
        private val busDriverId : TextView = itemView.findViewById(R.id.bus_driver_text_view)
        private val busBrandTextView : TextView = itemView.findViewById(R.id.bus_brand_text_view)

        fun bind(bus: Bus) {
            busStationTextView.text = bus.id.toString()
            busDriverId.text = bus.driverId.toString()
            busBrandTextView.text = bus.brand.toString()
        }
    }

}