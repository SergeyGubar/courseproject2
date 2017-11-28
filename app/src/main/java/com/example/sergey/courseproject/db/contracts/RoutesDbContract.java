package com.example.sergey.courseproject.db.contracts;

import android.provider.BaseColumns;

/**
 * Created by sgubar on 11/17/17.
 */

public class RoutesDbContract {
    public static final String TABLE_NAME = "routes";
    public static final String COLUMN_NUMBER = "routenumber";
    public static final String COLUMN_START_STATION_ID = "stationid";
    public static final String COLUMN_END_STATION_ID = "endid";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
