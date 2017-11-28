package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Station {
    private int mId;
    private String mName;
    private String mCity;

    public Station(int id, String name, String city) {
        mId = id;
        mName = name;
        mCity = city;
    }

    public Station(String name, String city) {
        mName = name;
        mCity = city;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCity() {
        return mCity;
    }
}
