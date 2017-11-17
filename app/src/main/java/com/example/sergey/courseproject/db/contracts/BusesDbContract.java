package com.example.sergey.courseproject.db.contracts;

import android.provider.BaseColumns;

/**
 * Created by sgubar on 11/17/17.
 */

public class BusesDbContract implements BaseColumns {
    public static final String TABLE_NAME = "buses";
    public static final String COLUMN_STATION_ID = "stationid";
    public static final String COLUMN_DRIVER_ID = "driverid";
    public static final String COLUMN_SEATS_NUMBER = "seatsnumber";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
