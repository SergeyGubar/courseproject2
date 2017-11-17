package com.example.sergey.courseproject.db.contracts;

import android.provider.BaseColumns;

/**
 * Created by sergey on 11/9/17.
 */

public class WorkerDbContract implements BaseColumns {
    private WorkerDbContract() {

    }

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_FULL_NAME = "fullname";
    public static final String COLUMN_PERSONAL_DATA = "personaldata";
    public static final String COLUMN_SALLARY = "sallary";
    public static final String COLUMN_EXPIRIENCE = "expirience";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_STATION_ID = "stationid";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
