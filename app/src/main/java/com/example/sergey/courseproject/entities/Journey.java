package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Journey {
    private int mId;
    private int mRoute;
    private int mCost;
    private String mDate;
    private int mBusId;

    public Journey(int id, int route, int cost, String date, int busId) {
        mId = id;
        mRoute = route;
        mCost = cost;
        mDate = date;
        mBusId = busId;
    }

    public int getId() {
        return mId;
    }

    public int getRoute() {
        return mRoute;
    }

    public int getCost() {
        return mCost;
    }

    public String getDate() {
        return mDate;
    }

    public int getBusId() {
        return mBusId;
    }
}
