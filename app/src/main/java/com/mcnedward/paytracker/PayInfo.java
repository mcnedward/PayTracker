package com.mcnedward.paytracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by Edward on 5/11/2015.
 */
public class PayInfo extends Activity {

    private TextView txtStartingTime;
    private TextView txtFinishingTime;
    private TextView txtTimeWorked;
    private TextView txtTimeLeft;
    private TextView txtPaySoFar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_info);

        txtStartingTime = (TextView) findViewById(R.id.txtStartingTime);
        txtFinishingTime = (TextView) findViewById(R.id.txtFinishingTime);
        txtTimeWorked = (TextView) findViewById(R.id.txtTimeWorked);
        txtTimeLeft = (TextView) findViewById(R.id.txtTimeLeft);
        txtPaySoFar = (TextView) findViewById(R.id.txtPaySoFar);

        TimeState timeState = (TimeState) getIntent().getSerializableExtra("timeState");
        if (timeState != null) {
            txtStartingTime.setText(timeState.getStartingTimeString());
            txtFinishingTime.setText(timeState.getFinishingTimeString());
        }
        Map<String, String> result = Helper.calculatePaySoFar(9, timeState);
        String info = "Pay: " + result.get("pay");
        txtTimeWorked.setText(result.get("timeElapsed"));
        txtTimeLeft.setText(result.get("timeLeft"));
        txtPaySoFar.setText(info);
    }

}
