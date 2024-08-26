package com.example.mah.clock;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    private Button countdownbtn;
    private Button stopwatchbtn;
    private ImageView MyImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        introduction();

        stopwatchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StopWatch MyStop=new StopWatch();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.clock_application,MyStop)
                        .addToBackStack(null)
                        .commit();

            }
        });

        countdownbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CountDownFrag myprogcount = new CountDownFrag();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.clock_application2,myprogcount)
                        .addToBackStack(null)
                        .commit();

            }
        });


    }


    private void introduction()
    {
        countdownbtn=(Button) findViewById(R.id.count_dwon);
        stopwatchbtn=(Button) findViewById(R.id.stop_watch);
    }
}
