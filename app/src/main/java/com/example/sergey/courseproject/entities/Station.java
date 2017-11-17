package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Station {
    private int mId;
    private int mNumberOfPersons;
    private int mNumberOfBuses;

    public Station(int id, int numberOfPersons, int numberOfBuses) {
        mId = id;
        mNumberOfPersons = numberOfPersons;
        mNumberOfBuses = numberOfBuses;
    }

    public int getId() {
        return mId;
    }

    public int getNumberOfPersons() {
        return mNumberOfPersons;
    }

    public int getNumberOfBuses() {
        return mNumberOfBuses;
    }
}
