package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergey.courseproject.db.contracts.BusesDbContract;
import com.example.sergey.courseproject.entities.Bus;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgubar on 11/17/17.
 */

public class BusesRepository {

    private Context mCtx;
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDb;


    public BusesRepository(Context ctx) {
        mCtx = ctx;
        mHelper = new SQLiteHelper(ctx);
    }

    public List<Bus> getAllBuses() {
        mDb = mHelper.getReadableDatabase();
        List<Bus> result = new ArrayList<>();
        Cursor buses = mDb.query(BusesDbContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (buses.moveToNext()) {
            Bus bus = new Bus(
                    buses.getInt(buses.getColumnIndex(BusesDbContract._ID)),
                    buses.getInt(buses.getColumnIndex(BusesDbContract.COLUMN_STATION_ID)),
                    buses.getInt(buses.getColumnIndex(BusesDbContract.COLUMN_DRIVER_ID)),
                    buses.getInt(buses.getColumnIndex(BusesDbContract.COLUMN_SEATS_NUMBER)),
                    buses.getString(buses.getColumnIndex(BusesDbContract.COLUMN_BRAND))
                    );
            result.add(bus);
        }
        buses.close();
        return result;
    }

    public long addBus(Bus bus) {
        mDb = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BusesDbContract.COLUMN_DRIVER_ID, bus.getDriverId());
        cv.put(BusesDbContract.COLUMN_BRAND, bus.getBrand());
        cv.put(BusesDbContract.COLUMN_SEATS_NUMBER, bus.getNumberOfSeats());
        cv.put(BusesDbContract.COLUMN_STATION_ID, bus.getStationId());

        return mDb.insert(BusesDbContract.TABLE_NAME, null, cv);
    }
}
