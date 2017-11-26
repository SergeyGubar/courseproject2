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

    public void updateBus(Bus bus) {
        long id = bus.getId();
        ContentValues cv = new ContentValues();
        cv.put(BusesDbContract._ID, id);
        cv.put(BusesDbContract.COLUMN_STATION_ID, bus.getStationId());
        cv.put(BusesDbContract.COLUMN_DRIVER_ID, bus.getDriverId());
        cv.put(BusesDbContract.COLUMN_SEATS_NUMBER, bus.getNumberOfSeats());
        cv.put(BusesDbContract.COLUMN_BRAND, bus.getBrand());
        mDb = mHelper.getWritableDatabase();
        mDb.update(BusesDbContract.TABLE_NAME,
                cv,
                BusesDbContract._ID + " = " + id,
                null
        );
    }

    public Bus getBusById(int id) {
        mDb = mHelper.getReadableDatabase();

        Cursor busCursor = mDb.query(BusesDbContract.TABLE_NAME,
                null,
                BusesDbContract._ID + " = " + id,
                null,
                null,
                null,
                null);

        busCursor.moveToFirst();
        Bus bus = new Bus(
                busCursor.getInt(busCursor.getColumnIndex(BusesDbContract._ID)),
                busCursor.getInt(busCursor.getColumnIndex(BusesDbContract.COLUMN_STATION_ID)),
                busCursor.getInt(busCursor.getColumnIndex(BusesDbContract.COLUMN_DRIVER_ID)),
                busCursor.getInt(busCursor.getColumnIndex(BusesDbContract.COLUMN_SEATS_NUMBER)),
                busCursor.getString(busCursor.getColumnIndex(BusesDbContract.COLUMN_BRAND))
        );

        busCursor.close();

        return bus;
    }

    public boolean deleteBus(Bus bus) {
        mDb = mHelper.getWritableDatabase();

        return mDb.delete(BusesDbContract.TABLE_NAME,
                BusesDbContract._ID + " = " + bus.getId(),
                null) > 0;

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
