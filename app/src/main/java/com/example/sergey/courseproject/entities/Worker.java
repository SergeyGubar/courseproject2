package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Worker {
    private int mId;
    private String mFullName;
    private int mPersonalData;
    private int mSalary;
    private int mYearsExperience;
    private String mTelephoneNumber;
    private int mStationId;
    private String mRole;
    private String mEmail;
    private String mPassword;


    public Worker(int id, String fullName, int personalData, int salary, int yearsExperience,
                  String telephoneNumber, int stationId, String role, String email, String password) {
        mId = id;
        mFullName = fullName;
        mPersonalData = personalData;
        mSalary = salary;
        mYearsExperience = yearsExperience;
        mTelephoneNumber = telephoneNumber;
        mStationId = stationId;
        mRole = role;
        mEmail = email;
        mPassword = password;
    }

    public Worker(String email, String password, String role, String name) {
        mEmail = email;
        mPassword = password;
        mRole = role;
        mFullName = name;
    }

    public Worker(String email, String password) {
        mEmail = email;
        mPassword = password;
    }



    public int getId() {
        return mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getFullName() {
        return mFullName;
    }

    public int getPersonalData() {
        return mPersonalData;
    }

    public int getSalary() {
        return mSalary;
    }

    public int getYearsExperience() {
        return mYearsExperience;
    }

    public String getTelephoneNumber() {
        return mTelephoneNumber;
    }

    public int getStationId() {
        return mStationId;
    }

    public String getRole() {
        return mRole;
    }
}
