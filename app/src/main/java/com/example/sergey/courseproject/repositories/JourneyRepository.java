package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergey.courseproject.db.contracts.JourneyDbContract;
import com.example.sergey.courseproject.entities.Journey;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

/**
 * Created by sgubar on 11/17/17.
 */

public class JourneyRepository {

    private Context mCtx;
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDb;

    public JourneyRepository(Context ctx) {
        mCtx = ctx;
        mHelper = new SQLiteHelper(mCtx);
    }

    public long addJourney(Journey journey) {
        mDb = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(JourneyDbContract.COLUMN_BUS_ID, journey.getBusId());
        cv.put(JourneyDbContract.COLUMN_COST, journey.getCost());
        cv.put(JourneyDbContract.COLUMN_DATE, journey.getDate());
        cv.put(JourneyDbContract.COLUMN_ROUTE_NUMBER, journey.getRoute());

        return mDb.insert(JourneyDbContract.TABLE_NAME, null, cv);
    }

    public Journey getJourneyById(int id) {
        mDb = mHelper.getReadableDatabase();
        Cursor cursor = mDb.query(JourneyDbContract.TABLE_NAME,
                null,
                JourneyDbContract._ID + " = " + id,
                null,
                null,
                null,
                null);
        Journey journey = new Journey(id,
                cursor.getInt(cursor.getColumnIndex(JourneyDbContract.COLUMN_ROUTE_NUMBER)),
                cursor.getInt(cursor.getColumnIndex(JourneyDbContract.COLUMN_COST)),
                cursor.getString(cursor.getColumnIndex(JourneyDbContract.COLUMN_DATE)),
                cursor.getInt(cursor.getColumnIndex(JourneyDbContract.COLUMN_BUS_ID)));
        cursor.close();
        return journey;
    }

}
