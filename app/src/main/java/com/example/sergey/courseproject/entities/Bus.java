package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Bus {
    private int mId;
    private int mStationId;
    private int mWorkerId;
    private int mNumberOfSeats;
    private String mBrand;

    public Bus(int id, int stationId, int workerId, int numberOfSeats, String brand) {
        mId = id;
        mStationId = stationId;
        mWorkerId = workerId;
        mNumberOfSeats = numberOfSeats;
        mBrand = brand;
    }

    public int getId() {
        return mId;
    }

    public int getStationId() {
        return mStationId;
    }

    public int getWorkerId() {
        return mWorkerId;
    }

    public int getNumberOfSeats() {
        return mNumberOfSeats;
    }

    public String getBrand() {
        return mBrand;
    }
}
