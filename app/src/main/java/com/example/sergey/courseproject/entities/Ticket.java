package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Ticket {
    private int mId;
    private int mJourneyId;
    private int mSeat;
    private String mTimeStamp;

    public Ticket() {
    }

    public void setId(int id) {
        mId = id;
    }

    public void setJourneyId(int journeyId) {
        mJourneyId = journeyId;
    }

    public void setSeat(int seat) {
        mSeat = seat;
    }

    public Ticket(int id, int journeyId, int seat) {
        mId = id;
        mJourneyId = journeyId;
        mSeat = seat;
    }

    public int getId() {
        return mId;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        mTimeStamp = timeStamp;
    }

    public int getJourneyId() {
        return mJourneyId;
    }

    public int getSeatNumber() {
        return mSeat;
    }

}
