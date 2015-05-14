package com.mcnedward.paytracker;

import android.util.Log;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 5/11/2015.
 */
public class Helper {

    private final static String TAG = "HELPER";

    public static double calculatePayByHour(int rate, int hours) {
        return rate * hours;
    }

    public static Map<String, String> calculatePaySoFar(int rate, TimeState timeState) {
        Map<String, String> result = new HashMap<>();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        double currentHour = cal.get(Calendar.HOUR_OF_DAY);
        double currentMinute = cal.get(Calendar.MINUTE);
        double currentTime = currentHour + (currentMinute / 60);

        double startingTime = timeState.getStartingTime();
        double finishingTime = timeState.getFinishingTime();

        double timeWorked = 0, timeRemaining = 0;

        if (currentTime > finishingTime) {
            Log.d(TAG, "Current time greater than finishing time.");
            timeWorked = finishingTime - startingTime;
            timeRemaining = 0;
        } else {
            Log.d(TAG, "Current time less than finishing time.");
            timeWorked = currentTime - timeState.getStartingTime();
            timeRemaining = timeState.getFinishingTime() - currentTime;
        }
        Map<String, Integer> startingTimes = getHoursAndMinutes(timeWorked);
        Map<String, Integer> finishingTimes = getHoursAndMinutes(timeRemaining);

        String timeElapsed = "Hours: " + startingTimes.get("hours") + "; Minutes: " + startingTimes.get("minutes");
        String timeLeft = "Hours: " + finishingTimes.get("hours") + "; Minutes: " + finishingTimes.get("minutes");

        String pay = convertToCurrency(timeWorked * rate);
        result.put("timeElapsed", timeElapsed);
        result.put("timeLeft", timeLeft);
        result.put("pay", pay);
        return result;
    }

    private static String convertToCurrency(double money) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(money);
    }

    private static Map<String, Integer> getHoursAndMinutes(double time) {
        Map<String, Integer> result = new HashMap<>();
        int minutes = (int) ((time - Math.floor(time)) * 60);
        int hours = (int) Math.floor(time);
        result.put("minutes", minutes);
        result.put("hours", hours);
        return result;
    }

}
