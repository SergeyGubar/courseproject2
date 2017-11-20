package com.example.sergey.courseproject.station;

import android.content.Context;

import com.example.sergey.courseproject.entities.Station;
import com.example.sergey.courseproject.repositories.StationRepository;

/**
 * Created by Sergey on 11/19/2017.
 */

public class AddStationActivityPresenter {
    private Context mCtx;
    private StationRepository mStationRepository;
    private AddStationActivityApi mApi;
    public AddStationActivityPresenter(Context ctx, AddStationActivityApi api) {
        mCtx = ctx;
        mApi = api;
        mStationRepository = new StationRepository(ctx);
    }

    public void addStation(Station station) {
        long id = mStationRepository.addStation(station);

        if (id < 0) {
            mApi.showToast("Station add failed!");
        } else {
            mApi.showToast("Station with id " + id + " added!");
            mApi.clearInputs();
        }

    }
}
