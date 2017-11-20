package com.example.sergey.courseproject.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sergey.courseproject.db.contracts.BusesDbContract;
import com.example.sergey.courseproject.db.contracts.JourneyDbContract;
import com.example.sergey.courseproject.db.contracts.RoutesDbContract;
import com.example.sergey.courseproject.db.contracts.StationDbContract;
import com.example.sergey.courseproject.db.contracts.TicketDbContract;
import com.example.sergey.courseproject.db.contracts.WorkerDbContract;

/**
 * Created by sergey on 11/9/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHelper";
    private static final String DATABASE_NAME = "autostation.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initializeWorkersTable(db);
        initializeBusesTable(db);
        initializeJourneysTable(db);
        initializeTicketsTable(db);
        initializeStationsTable(db);
        initializeRoutesTable(db);
    }


    private void initializeWorkersTable(SQLiteDatabase db) {
        final String usersTableSqlQuery = "CREATE TABLE " +
                WorkerDbContract.TABLE_NAME + "( " +
                WorkerDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WorkerDbContract.COLUMN_EMAIL + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_ROLE + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_EXPERIENCE + " INTEGER, " +
                WorkerDbContract.COLUMN_PERSONAL_DATA + " INTEGER, " +
                WorkerDbContract.COLUMN_STATION_ID + " INTEGER NOT NULL, " +
                WorkerDbContract.COLUMN_TELEPHONE + " INTEGER, " +
                WorkerDbContract.COLUMN_SALARY + " INTEGER, " +
                WorkerDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + WorkerDbContract.COLUMN_STATION_ID + ") REFERENCES " +
                StationDbContract.TABLE_NAME + "(" + StationDbContract._ID + ")" +
                "); ";
        db.execSQL(usersTableSqlQuery);
    }

    private void initializeBusesTable(SQLiteDatabase db) {
        final String busesTableSqlQuery = "CREATE TABLE " +
                BusesDbContract.TABLE_NAME + "( " +
                BusesDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BusesDbContract.COLUMN_STATION_ID + " INTEGER, " +
                BusesDbContract.COLUMN_DRIVER_ID + " INTEGER, " +
                BusesDbContract.COLUMN_SEATS_NUMBER + " INTEGER NOT NULL, " +
                BusesDbContract.COLUMN_BRAND + " TEXT NOT NULL, " +
                BusesDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + BusesDbContract.COLUMN_DRIVER_ID + ") REFERENCES " +
                WorkerDbContract.TABLE_NAME + "(" + WorkerDbContract._ID + ")," +
                "FOREIGN KEY (" + BusesDbContract.COLUMN_STATION_ID + ") REFERENCES " +
                StationDbContract.TABLE_NAME + "(" + StationDbContract._ID + ")" +
                "); ";
        db.execSQL(busesTableSqlQuery);
    }

    private void initializeStationsTable(SQLiteDatabase db) {
        final String stationsTableSqlQuery = "CREATE TABLE " +
                StationDbContract.TABLE_NAME + "( " +
                StationDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StationDbContract.COLUMN_CITY + " TEXT NOT NULL, " +
                StationDbContract.COLUMN_NAME + " TEXT NOT NULL, " +
                StationDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                "); ";
        db.execSQL(stationsTableSqlQuery);
    }

    private void initializeRoutesTable(SQLiteDatabase db) {
        final String routesTableSqlQuery = "CREATE TABLE " +
                RoutesDbContract.TABLE_NAME + "( " +
                RoutesDbContract.COLUMN_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RoutesDbContract.COLUMN_START_STATION_ID + " INTEGER NOT NULL, " +
                RoutesDbContract.COLUMN_END_STATION_ID + " INTEGER NOT NULL, " +
                RoutesDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + RoutesDbContract.COLUMN_START_STATION_ID + ") REFERENCES " +
                StationDbContract.TABLE_NAME + "(" + StationDbContract._ID + "), " +
                "FOREIGN KEY (" + RoutesDbContract.COLUMN_END_STATION_ID + ") REFERENCES " +
                StationDbContract.TABLE_NAME + "(" + StationDbContract._ID + ")" +
                "); ";
        db.execSQL(routesTableSqlQuery);
    }

    private void initializeJourneysTable(SQLiteDatabase db) {
        final String journeysTableSqlQuery = "CREATE TABLE " +
                JourneyDbContract.TABLE_NAME + "( " +
                JourneyDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                JourneyDbContract.COLUMN_ROUTE_NUMBER + " INTEGER NOT NULL, " +
                JourneyDbContract.COLUMN_COST + " INTEGER NOT NULL, " +
                JourneyDbContract.COLUMN_BUS_ID + " INTEGER NOT NULL, " +
                JourneyDbContract.COLUMN_DATE + " TEXT NOT NULL, " +
                JourneyDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + JourneyDbContract.COLUMN_BUS_ID + ") REFERENCES " +
                BusesDbContract.TABLE_NAME + "(" + BusesDbContract._ID + ")," +
                "FOREIGN KEY (" + JourneyDbContract.COLUMN_ROUTE_NUMBER + ") REFERENCES " +
                RoutesDbContract.TABLE_NAME + "(" + RoutesDbContract.COLUMN_NUMBER + ")" +
                "); ";
        db.execSQL(journeysTableSqlQuery);
    }

    private void initializeTicketsTable(SQLiteDatabase db) {
        final String ticketsSqlQuery = "CREATE TABLE " +
                TicketDbContract.TABLE_NAME + "( " +
                TicketDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TicketDbContract.COLUMN_JOURNEY_ID + " INTEGER NOT NULL, " +
                TicketDbContract.COLUMN_SEAT_NUMBER + " INTEGER NOT NULL, " +
                TicketDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + TicketDbContract._ID+ ") REFERENCES " +
                JourneyDbContract.TABLE_NAME + "(" + JourneyDbContract._ID + ")" +
                "); ";
        db.execSQL(ticketsSqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String usersUpdateSqlQuery = "DROP TABLE IF EXISTS " + WorkerDbContract.TABLE_NAME;
        db.execSQL(usersUpdateSqlQuery);
    }

}
