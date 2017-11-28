package com.example.sergey.courseproject.db.contracts;

import android.provider.BaseColumns;

/**
 * Created by sgubar on 11/17/17.
 */

public class StationDbContract implements BaseColumns {
    public static final String TABLE_NAME = "stations";
    public static final String COLUMN_NAME = "stationname";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
