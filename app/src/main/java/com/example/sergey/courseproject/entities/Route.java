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

    public Route() {
    }

    public int getNumber() {
        return mNumber;
    }

    public int getStartStationId() {
        return mStartStationId;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public void setStartStationId(int startStationId) {
        mStartStationId = startStationId;
    }

    public void setEndStationId(int endStationId) {
        mEndStationId = endStationId;
    }

    public int getEndStationId() {
        return mEndStationId;
    }
}
