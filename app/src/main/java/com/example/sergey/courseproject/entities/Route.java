package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Route {
    private int mNumber;
    private int mStartStationId;
    private int mEndStationId;

    public Route(int number, int startStationId, int endStationId) {
        mNumber = number;
        mStartStationId = startStationId;
        mEndStationId = endStationId;
    }

    public int getNumber() {
        return mNumber;
    }

    public int getStartStationId() {
        return mStartStationId;
    }

    public int getEndStationId() {
        return mEndStationId;
    }
}
