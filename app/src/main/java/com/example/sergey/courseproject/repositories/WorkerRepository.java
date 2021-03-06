package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sergey.courseproject.db.contracts.WorkerDbContract;
import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgubar on 11/17/17.
 */

public class WorkerRepository {

    private static final String TAG = "WorkerRepository";
    private SQLiteDatabase mDb;
    private SQLiteHelper mHelper;


    public WorkerRepository(Context ctx) {
        mHelper = new SQLiteHelper(ctx);
    }

    public long addWorker(Worker user) {
        mDb = mHelper.getWritableDatabase();
        Log.d(TAG, "addWorker: user's email " + user.getEmail());
        Cursor usersWithTheSameEmail = mDb.query(WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_EMAIL + " =" + "\"" + user.getEmail() + "\"",
                null,
                null,
                null,
                null);
        long id = -1;

        Log.d(TAG, "addWorker: users with the same email " + usersWithTheSameEmail.getCount());
        if (usersWithTheSameEmail.getCount() == 0) {
            ContentValues cv = new ContentValues();
            cv.put(WorkerDbContract.COLUMN_EMAIL, user.getEmail());
            cv.put(WorkerDbContract.COLUMN_PASSWORD, user.getPassword());
            cv.put(WorkerDbContract.COLUMN_ROLE, user.getRole());
            cv.put(WorkerDbContract.COLUMN_FULL_NAME, user.getFullName());
            cv.put(WorkerDbContract.COLUMN_STATION_ID, user.getStationId());
            id = mDb.insert(WorkerDbContract.TABLE_NAME, null, cv);
        }
        usersWithTheSameEmail.close();
        if (id < 0) {
            Log.d(TAG, "addWorker: user wasn't successfully added!");
        }
        return id;
    }

    public boolean deleteWorker(long id) {
        mDb = mHelper.getWritableDatabase();
        return mDb.delete(WorkerDbContract.TABLE_NAME,
                WorkerDbContract._ID + " = " + id,
                null) > 0;
    }

    public String getWorkerRole(Worker worker) {
        mDb = mHelper.getReadableDatabase();
        Cursor workers = mDb.query(
                WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_EMAIL + " = " + "\"" + worker.getEmail() + "\"" + " AND " +
                        WorkerDbContract.COLUMN_PASSWORD + " = " + "\"" + worker.getPassword() + "\"",
                null,
                null,
                null,
                null
        );
        String role = "";
        if (workers.getCount() > 0) {
            workers.moveToFirst();
            role = workers.getString(workers.getColumnIndex(WorkerDbContract.COLUMN_ROLE));
            workers.close();
        }
        return role;
    }

    public List<Worker> getWorkersWithRole(String role, String order) {
        mDb = mHelper.getReadableDatabase();

        List<Worker> result = new ArrayList<>();
        Cursor data = mDb.query(WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_ROLE + " = " + "\"" + role + "\"",
                null,
                null,
                null,
                order,
                null);
        while (data.moveToNext()) {
            Worker worker = new Worker(
                    data.getInt(data.getColumnIndex(WorkerDbContract._ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_FULL_NAME)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_PERSONAL_DATA)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_SALARY)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_EXPERIENCE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_TELEPHONE)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_STATION_ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_ROLE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_EMAIL)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_PASSWORD))
            );
            result.add(worker);
        }
        data.close();
        return result;
    }

    public boolean editWorker(Worker worker) {
        mDb = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WorkerDbContract.COLUMN_EMAIL, worker.getEmail());
        cv.put(WorkerDbContract.COLUMN_PASSWORD, worker.getPassword());
        cv.put(WorkerDbContract.COLUMN_ROLE, worker.getRole());
        cv.put(WorkerDbContract.COLUMN_FULL_NAME, worker.getFullName());
        cv.put(WorkerDbContract.COLUMN_STATION_ID, worker.getStationId());
        cv.put(WorkerDbContract.COLUMN_EXPERIENCE, worker.getYearsExperience());
        cv.put(WorkerDbContract.COLUMN_SALARY, worker.getSalary());
        cv.put(WorkerDbContract.COLUMN_TELEPHONE, worker.getTelephoneNumber());
        cv.put(WorkerDbContract.COLUMN_PERSONAL_DATA, worker.getPersonalData());
        boolean wereColumnsUpdated = mDb.update(WorkerDbContract.TABLE_NAME,
                cv,
                WorkerDbContract._ID + " = " + worker.getId(),
                null
        ) > 0;
        return wereColumnsUpdated;
    }

    public List<Worker> getAllWorkers(String order) {
        mDb = mHelper.getReadableDatabase();
        List<Worker> result = new ArrayList<>();
        Cursor data = mDb.query(WorkerDbContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                order,
                null);
        while (data.moveToNext()) {
            Worker worker = new Worker(
                    data.getInt(data.getColumnIndex(WorkerDbContract._ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_FULL_NAME)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_PERSONAL_DATA)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_SALARY)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_EXPERIENCE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_TELEPHONE)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_STATION_ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_ROLE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_EMAIL)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_PASSWORD))
            );
            result.add(worker);
        }
        return result;
    }

    public List<Worker> getWorkersWithRoleAndStation(String role, String orderByFilter, String station) {
        mDb = mHelper.getReadableDatabase();

        List<Worker> result = new ArrayList<>();
        Cursor data = mDb.query(WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_ROLE + " = " + "\"" + role + "\""
                        + " AND " + WorkerDbContract.COLUMN_STATION_ID + " = " + Integer.valueOf(station),
                null,
                null,
                null,
                orderByFilter,
                null);
        while (data.moveToNext()) {
            Worker worker = new Worker(
                    data.getInt(data.getColumnIndex(WorkerDbContract._ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_FULL_NAME)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_PERSONAL_DATA)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_SALARY)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_EXPERIENCE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_TELEPHONE)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_STATION_ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_ROLE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_EMAIL)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_PASSWORD))
            );
            result.add(worker);
        }
        data.close();
        return result;
    }

    public List<Worker> getWorkersWithStation(String orderByFilter, String station) {
        mDb = mHelper.getReadableDatabase();

        List<Worker> result = new ArrayList<>();
        Cursor data = mDb.query(WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_STATION_ID + " = " + Integer.valueOf(station),
                null,
                null,
                null,
                orderByFilter,
                null);
        while (data.moveToNext()) {
            Worker worker = new Worker(
                    data.getInt(data.getColumnIndex(WorkerDbContract._ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_FULL_NAME)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_PERSONAL_DATA)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_SALARY)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_EXPERIENCE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_TELEPHONE)),
                    data.getInt(data.getColumnIndex(WorkerDbContract.COLUMN_STATION_ID)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_ROLE)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_EMAIL)),
                    data.getString(data.getColumnIndex(WorkerDbContract.COLUMN_PASSWORD))
            );
            result.add(worker);
        }
        data.close();
        return result;
    }
}
