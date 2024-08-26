package com.example.mah.clock;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopWatch extends Fragment
{
    Button StartButton;
    Button StopButton;
    TextView oclock;
    int second = 0;
    int millisecond=0;
    int minute=0;
    int hour=0;
    int a;
    CountDownTimer mycount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stop_watch,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartButton.setEnabled(false);
                StopButton.setEnabled(true);
                timing();



            }
        });
        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                StartButton.setEnabled(true);
                mycount.cancel();
                second = 00;
                millisecond = 00;
                minute = 00;
                StopButton.setEnabled(false);



            }
        });




    }


    private void introduction(View view)
    {
        StartButton=(Button)view.findViewById(R.id.start);
        StopButton=(Button) view.findViewById(R.id.stop);
        oclock=(TextView) view.findViewById(R.id.txt);
    }
    private void timing()
    {

         mycount=new CountDownTimer(100000000,1) {
            @Override
            public void onTick(long clock)
            {


               millisecond++;
               if(millisecond==55)
               {
                   second++;
                   millisecond=00;
                   if(second == 60){
                       minute++;
                       second = 0;
                   }
                   if(minute==60)
                   {
                       hour++;
                       minute=0;
                   }
               }

               oclock.setText("Time is : " +hour +" . "+ minute + " . " +second +" . "+millisecond);
            }

            @Override
            public void onFinish()
            {

            }
        };
        mycount.start();
    }
}
