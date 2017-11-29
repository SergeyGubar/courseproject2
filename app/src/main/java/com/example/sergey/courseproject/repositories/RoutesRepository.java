package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sergey.courseproject.db.contracts.RoutesDbContract;
import com.example.sergey.courseproject.entities.Route;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgubar on 11/17/17.
 */

public class RoutesRepository {
    private Context mCtx;
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDb;
    private static final String TAG = "RoutesRepository";
    public RoutesRepository(Context ctx) {
        mCtx = ctx;
        mHelper = new SQLiteHelper(ctx);

    }

    public void add(@NotNull Route route) {
        mDb = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RoutesDbContract.COLUMN_END_STATION_ID, route.getEndStationId());
        cv.put(RoutesDbContract.COLUMN_NUMBER, route.getNumber());
        cv.put(RoutesDbContract.COLUMN_START_STATION_ID, route.getStartStationId());
        if (mDb.insert(RoutesDbContract.TABLE_NAME,
                null,
                cv) < 0) {
            Log.d(TAG, "add: Route wasn't added");
        }
    }

    public List<Route> getAllRoutes(String orderBy) {
        mDb = mHelper.getReadableDatabase();
        List<Route> result = new ArrayList<>();

        Cursor routes = mDb.query(RoutesDbContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                orderBy);

        while (routes.moveToNext()) {
            Route route = new Route(routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_NUMBER)),
                    routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_START_STATION_ID)),
                    routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_END_STATION_ID)));
            result.add(route);
        }
        routes.close();
        return result;
    }
    public List<CharSequence> getAllRoutesNumbers(String orderBy) {
        mDb = mHelper.getReadableDatabase();
        List<CharSequence> result = new ArrayList<>();

        Cursor routes = mDb.query(RoutesDbContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                orderBy);

        while (routes.moveToNext()) {
            Route route = new Route(routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_NUMBER)),
                    routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_START_STATION_ID)),
                    routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_END_STATION_ID)));
            result.add(String.valueOf(route.getNumber()));
        }
        routes.close();
        return result;
    }

    public Route getRouteByNumber(int id) {
        mDb = mHelper.getReadableDatabase();

        Cursor routes = mDb.query(RoutesDbContract.TABLE_NAME,
                null,
                RoutesDbContract.COLUMN_NUMBER + " = " + id,
                null,
                null,
                null,
                null);
        routes.moveToFirst();


        Route route = new Route(routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_NUMBER)),
                routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_START_STATION_ID)),
                routes.getInt(routes.getColumnIndex(RoutesDbContract.COLUMN_END_STATION_ID)));
        routes.close();
        return route;
    }
}
