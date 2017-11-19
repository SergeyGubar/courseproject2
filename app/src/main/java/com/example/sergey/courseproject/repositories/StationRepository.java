package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sergey.courseproject.db.contracts.StationDbContract;
import com.example.sergey.courseproject.entities.Station;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

/**
 * Created by sgubar on 11/17/17.
 */

public class StationRepository {
    private static final String TAG = "StationRepository";
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDb;

    public StationRepository(Context ctx) {
        mHelper = new SQLiteHelper(ctx);
    }

    public long addStation(Station station) {
        mDb = mHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(StationDbContract.COLUMN_NAME, station.getName());
        cv.put(StationDbContract.COLUMN_CITY, station.getCity());

        long id = mDb.insert(StationDbContract.TABLE_NAME,
                null,
                cv);
        if (id < 0) {
            Log.d(TAG, "Station wasn't added to db!");
            Log.d(TAG, station.getName() + " " + station.getCity());
        }
        return id;
    }
}
