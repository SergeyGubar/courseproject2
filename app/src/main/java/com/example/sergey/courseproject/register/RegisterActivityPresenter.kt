package com.example.sergey.courseproject.register

import android.content.Context
import android.widget.ArrayAdapter
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.repositories.StationRepository
import com.example.sergey.courseproject.repositories.WorkerRepository
import java.util.ArrayList

/**
 * Created by sergey on 11/9/17.
 */

class RegisterActivityPresenter(private val mCtx: Context, private val mApi: RegisterActivityApi,
                                private val mWorkerRepository : WorkerRepository = WorkerRepository(mCtx),
                                private val mStationRepository : StationRepository = StationRepository(mCtx)) {

    fun addUser() {
        val user = mApi.worker
        val userId = mWorkerRepository.addWorker(user)
        if(userId < 0) {
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
        val stationNames = mStationRepository.stationsNames

        val adapter = ArrayAdapter<CharSequence>(mCtx, android.R.layout.simple_spinner_item, stationNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
    }
}
