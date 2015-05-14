package com.mcnedward.paytracker;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private TextView txtStartingTime;
    private TextView txtFinishingTime;
    private TextView txtInfo;

    private Activity activity;

    private TimeState timeState;
    private boolean startingSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        timeState = new TimeState();

        txtStartingTime = (TextView) findViewById(R.id.txtStartingTime);
        txtFinishingTime = (TextView) findViewById(R.id.txtFinishingTime);
        txtInfo = (TextView) findViewById(R.id.txtInfo);

        txtStartingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog picker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (!validateStartingTime(hourOfDay, minute)) {
                            txtInfo.setText("Your starting time has to be before the current time!");
                        } else {
                            txtInfo.setText("");
                            txtStartingTime.setText(timeState.getStartingTimeString());
                        }
                    }
                }, timeState.getStartingHour(), timeState.getStartingMinute(), false);
                picker.show();
            }
        });
        txtFinishingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog picker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (!validateFinishingTime(hourOfDay, minute)) {
                            txtInfo.setText("Your finishing time has to be after your starting time!");
                        } else {
                            txtInfo.setText("");
                            txtFinishingTime.setText(timeState.getFinishingTimeString());
                        }
                    }
                }, timeState.getFinishingHour(), timeState.getFinishingMinute(), false);
                picker.show();
            }
        });

        Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startingHour = timeState.getStartingHour();
                int startingMinute = timeState.getStartingMinute();
                int finishingHour = timeState.getFinishingHour();
                int finishingMinute = timeState.getFinishingMinute();
                if (startingHour == finishingHour && startingMinute == finishingMinute) {
                    txtInfo.setText("Your starting and finishing times have to be different!");
                    return;
                }
                startPayInfo();
            }
        });
    }

    private boolean validateStartingTime(int selectedHour, int selectedMinute) {
        Map<String, Integer> times = getCurrentHourAndMinute();
        int currentHour = times.get("currentHour");
        int currentMinute = times.get("currentMinute");
        if (currentHour < selectedHour)
            return false;
        else {
            if (currentMinute < selectedMinute)
                return false;
            else {
                timeState.setStartingTimes(selectedHour, selectedMinute);
                return true;
            }
        }
    }

    private boolean validateFinishingTime(int selectedHour, int selectedMinute) {
        int startingHour = timeState.getStartingHour();
        int startingMinute = timeState.getStartingMinute();
        if (startingHour > selectedHour)
            return false;
        else {
            if (startingMinute > selectedMinute)
                return false;
            else {
                timeState.setFinishingTimes(selectedHour, selectedMinute);
                return true;
            }
        }
    }

    /**
     * Get the current hour and the current minute.
     *
     * @return A map containing the current hour (key: currentHour) and current minute (key: currentMinute).
     */
    private Map<String, Integer> getCurrentHourAndMinute() {
        Map<String, Integer> times = new HashMap<>();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        int currentMinute = cal.get(Calendar.MINUTE);
        times.put("currentHour", currentHour);
        times.put("currentMinute", currentMinute);
        return times;
    }

    private void startPayInfo() {
        Intent payInfo = new Intent(this, PayInfo.class);
        payInfo.putExtra("timeState", timeState);
        startActivity(payInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
