package com.example.sergey.courseproject.station;

import android.content.Context;

import com.example.sergey.courseproject.entities.Station;
import com.example.sergey.courseproject.repositories.StationRepository;

/**
 * Created by Sergey on 11/19/2017.
 */

public class StationActivityPresenter {
    private Context mCtx;
    private StationRepository mStationRepository;

    public StationActivityPresenter(Context ctx) {
        mCtx = ctx;
        mStationRepository = new StationRepository(ctx);
    }

    public void addStation(Station station) {
        mStationRepository.addStation(station);
    }
}
