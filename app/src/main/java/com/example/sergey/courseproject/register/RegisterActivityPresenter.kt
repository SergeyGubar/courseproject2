package com.example.sergey.courseproject.register

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.repositories.StationRepository
import com.example.sergey.courseproject.repositories.WorkerRepository

/**
 * Created by sergey on 11/9/17.
 */

class RegisterActivityPresenter(private val mCtx: Context,
                                private val mApi: RegisterActivityApi,
                                private val mWorkerRepository : WorkerRepository = WorkerRepository(mCtx),
                                private val mStationRepository : StationRepository = StationRepository(mCtx)) {

    fun addUser() {
        val worker = mApi.worker
        val workerId = mWorkerRepository.addWorker(worker)
        if(workerId < 0) {
            mApi.showFailMessage()
        } else {
            mApi.showSuccessToast()
        }
        mApi.cleanInputs()
    }

    fun initializeRolesSpinner() {
        val spinner = mApi.rolesSpinner
        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter
                .createFromResource(mCtx,
                        R.array.roles_items, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun initializeStationSpinner() {
        val spinner = mApi.stationsSpinner
        val stationNames = mStationRepository.stationIds
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, itemPosition: Int, p3: Long) {
                val stationId = spinner.getItemAtPosition(itemPosition).toString().toInt()
                val station = mStationRepository.getStationById(stationId)
                mApi.displayStationInfo(station)
            }
        }
        val adapter = ArrayAdapter<CharSequence>(mCtx, android.R.layout.simple_spinner_item, stationNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
    }
}
