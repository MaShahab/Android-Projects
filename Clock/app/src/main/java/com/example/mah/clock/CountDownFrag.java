package com.example.mah.clock;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.itangqi.waveloadingview.WaveLoadingView;

public class CountDownFrag extends Fragment
{
    private TextView MyFirstText;
    private TextView MyText;
    private TextView RemainTime;
    private TextView TimeUp;

    private EditText MyEditText;
    private EditText MyEditText2;

    private Button StartButton;
    private Button StopButton;

    private String countstr;
    private String countstr2;

    private Integer countstrint;
    private Integer countstrint2;
    private Integer sixtheen=60;

    private long i=0;
    private CountDownTimer mytime;
    private CountDownTimer myrealtime;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countdown,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);


        startbtn();


        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartButton.setText("Resume");
                mytime.cancel();



            }
        });



    }

    private void introduction(View view)
    {
        MyFirstText=(TextView) view.findViewById(R.id.my_first_txt);
        MyText=(TextView) view.findViewById(R.id.my_text);
        RemainTime=(TextView) view.findViewById(R.id.remain_time);
        MyEditText=(EditText) view.findViewById(R.id.my_edit_text);
        MyEditText2=(EditText) view.findViewById(R.id.my_edit_tex2);
        StartButton=(Button) view.findViewById(R.id.start_button);
        StopButton=(Button) view.findViewById(R.id.stop_button);
        TimeUp=(TextView) view.findViewById(R.id.time_up_txt);
    }

    private void startbtn()
    {

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countdowntime();


            }
        });
    }

    private void countdowntime()
    {

        countstr=MyEditText.getText().toString();
        countstr2=MyEditText2.getText().toString();

        countstrint=Integer.parseInt(countstr);
        countstrint2=Integer.parseInt(countstr2);


        mytime = new CountDownTimer(countstrint*1000,1000) {
            @Override
            public void onTick(long timing)
            {
                if(countstrint2!=0&&countstrint!=0) {
                    countstrint--;

                    if (countstrint == 0) {
                        countstrint = 60;
                        countstrint2--;
                        if(countstrint==60)
                        {
                            realtime();
                        }
                    }


                    RemainTime.setText("Remain time :" + countstrint2 + " : " + countstrint);


                }




            }

            @Override
            public void onFinish()
            {

            }
        };
        mytime.start();



    }
    private void realtime()
    {
        myrealtime=new CountDownTimer(100000000,1000) {
            @Override
            public void onTick(long realtimer)
            {
                if(sixtheen==0){sixtheen=60; countstrint2--;}
                sixtheen--;

                RemainTime.setText("Remain time :" + countstrint2 + " : " + sixtheen);

                StopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myrealtime.cancel();
                    }
                });

                if( countstrint2<0)
                {
                    RemainTime.setText("Remain time :" + "0" + " : " + "0");
                    TimeUp.setText("Time is up !");
                }




            }

            @Override
            public void onFinish() {

            }
        };
        myrealtime.start();


    }
    private boolean isnumber(String str)
    {
        if(str.length()==0)
        {return false;}
        return true;

    }



}
