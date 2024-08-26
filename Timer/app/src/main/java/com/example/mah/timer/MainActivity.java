package com.example.mah.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private TextView timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timing=(findViewById(R.id.my_timimg));


        CountDownTimer mycount =new CountDownTimer(8000,1000) {
            @Override
            public void onTick(long remainingtime) {

                timing.setText("YOur remaning time is :" +remainingtime/1000);

            }

            @Override
            public void onFinish() {

                timing.setText("Unfurtuntelly your time is finished");

            }
        };
        mycount.start();


    }
}
