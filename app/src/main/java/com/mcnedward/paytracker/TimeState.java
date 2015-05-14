package com.mcnedward.paytracker;

import java.io.Serializable;

/**
 * Created by Edward on 5/11/2015.
 */
public class TimeState implements Serializable {

    private String mStartingTimeString;
    private int mStartingHour;
    private int mStartingMinute;
    private double mStartingTime;

    private String mFinishingTimeString;
    private int mFinishingHour;
    private int mFinishingMinute;
    private double mFinishingTime;

    public TimeState() {
        setStartingTimes(12, 0);
        setFinishingTimes(12, 0);
    }

    /**
     * Set the starting times.
     *
     * @param startingHour   The starting hour.
     * @param startingMinute The starting minute.
     */
    public void setStartingTimes(int startingHour, int startingMinute) {
        mStartingTimeString = getTimeString(startingHour, startingMinute);
        mStartingHour = startingHour;
        mStartingMinute = startingMinute;
        mStartingTime = getTime(startingHour, startingMinute);
    }

    /**
     * Set the finishing times.
     *
     * @param finishingHour   The finishing hour.
     * @param finishingMinute The finishing minute.
     */
    public void setFinishingTimes(int finishingHour, int finishingMinute) {
        mFinishingTimeString = getTimeString(finishingHour, finishingMinute);
        mFinishingHour = finishingHour;
        mFinishingMinute = finishingMinute;
        mFinishingTime = getTime(finishingHour, finishingMinute);
    }

    private String getTimeString(int hour, int minute) {
        String timeResult;
        String period = "AM";
        if (hour > 12) {
            hour -= 12;
            period = "PM";
        }
        if (hour == 0)
            hour = 12;
        timeResult = hour + ":" + (minute < 10 ? "0" + minute : minute) + " " + period;
        return timeResult;
    }

    private double getTime(int hour, int minute) {
        return hour + (minute / 60);
    }

    public String getStartingTimeString() {
        return mStartingTimeString;
    }

    public void setStartingTimeString(String startingTimeString) {
        mStartingTimeString = startingTimeString;
    }

    public int getStartingHour() {
        return mStartingHour;
    }

    public void setStartingHour(int startingHour) {
        mStartingHour = startingHour;
    }

    public int getStartingMinute() {
        return mStartingMinute;
    }

    public void setStartingMinute(int startingMinute) {
        mStartingMinute = startingMinute;
    }


    public double getStartingTime() {
        return mStartingTime;
    }

    public void setStartingTime(double startingTime) {
        mStartingTime = startingTime;
    }

    public String getFinishingTimeString() {
        return mFinishingTimeString;
    }

    public void setFinishingTimeString(String finishingTime) {
        mFinishingTimeString = finishingTime;
    }

    public int getFinishingHour() {
        return mFinishingHour;
    }

    public void setFinishingHour(int finishingHour) {
        mFinishingHour = finishingHour;
    }

    public int getFinishingMinute() {
        return mFinishingMinute;
    }

    public void setFinishingMinute(int finishingMinute) {
        mFinishingMinute = finishingMinute;
    }

    public double getFinishingTime() {
        return mFinishingTime;
    }

    public void setFinishingTime(double finishingTime) {
        mFinishingTime = finishingTime;
    }
}
