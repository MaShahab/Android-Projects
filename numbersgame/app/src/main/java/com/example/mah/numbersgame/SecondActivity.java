package com.example.mah.whichoneislarger;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private TextView score;
    private TextView level;
    private TextView timeremainig;
    private TextView output;

    private Button leftbutton;
    private Button rightbutton;
    private Button equalbutton;
    private Button buttonout;

    private int firstint=0;
    private int secondint=0;
    private int levelpoints=-1;
    private int totalscore=-1;
    private int leftnumberint;
    private int rightnumberint;
    private int qqqq=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        introduction();

        Timing();


        leftnumbergeneration();
        leftnumberint=firstint;
        rightnumbergeneration();
        rightnumberint=secondint;




        leftonclick();
        evaluate1(0);


        rightonclick();
        evaluate1(1);


        equalonclick();
        evaluate1(2);

        onclickoutput();


    }

    private void introduction()
    {
        score=(TextView) findViewById(R.id.game_score);
        level=(TextView) findViewById(R.id.game_level);
        timeremainig=(TextView) findViewById(R.id.Time_Remaining);
        leftbutton=(Button) findViewById(R.id.Left_button);
        rightbutton=(Button) findViewById(R.id.Right_button);
        equalbutton=(Button) findViewById(R.id.equal_button);
        output=(TextView) findViewById(R.id.out_put);
        buttonout=(Button) findViewById(R.id.button_out);






    }
    private void leftnumbergeneration()
    {

        levelround();


        Random rand=new Random();
        firstint=rand.nextInt();
        leftbutton.setText(String.valueOf(leftnumberint));

        if(firstint<0)
        {
            firstint=firstint*-1;
        }
        firstint=firstint%40;
        leftbutton.setText(String.valueOf(firstint));
        leftnumberint=firstint;






    }
    private int rightnumbergeneration(){


        Random rand=new Random();
        secondint=rand.nextInt();

        rightbutton.setText(String.valueOf(rightnumberint));
        if(levelpoints>=10)
        {

        }
        if(secondint<0)
        {
            secondint=secondint*-1;
        }
        secondint=secondint%40;
        rightbutton.setText(String.valueOf(secondint));
        rightnumberint=secondint;
        return secondint;

    }
    private void leftonclick()
    {
        leftbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                evaluate1(0);


                if(levelpoints>=10)
                {
                    return;
                }

                leftnumbergeneration();
                rightnumbergeneration();


            }
        });
    }
    private void rightonclick()
    {
        rightbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(levelpoints>=10)
                {
                    return;
                }
                evaluate1(1);
                leftnumbergeneration();
                rightnumbergeneration();

            }
        });
    }
    private void equalonclick()
    {
        equalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(levelpoints>=10)
                {
                    return;
                }
                evaluate1(2);
                leftnumbergeneration();
                rightnumbergeneration();

            }
        });
    }
    private void levelround()

    {
        levelpoints++;
        level.setText("The round is :" + levelpoints);
        if(levelpoints>=10)
        {
            level.setText("The round finished!");
        }

    }
    private void evaluate1(int whatpressed)
    {
        if(whatpressed==0)
        {

            if(levelpoints>=10)
            {
                return;
            }


            if(leftnumberint>rightnumberint)
            {
                totalscore++;
                score.setText(getString(R.string.user_points) + totalscore +" of 10");



            }


        }
        else if(whatpressed==1)
        {
            if(levelpoints>=10)
            {
                return;
            }
            if(rightnumberint>leftnumberint)
            {
                totalscore++;
                score.setText(getString(R.string.user_points) + totalscore +" of 10");
            }

        }
        else if (whatpressed==2)
        {
            if(levelpoints>=10)
            {
                return;
            }
            if(leftnumberint==rightnumberint)
            {
                totalscore++;
                score.setText(getString(R.string.user_points) + totalscore +" of 10");
            }

        }

    }
    private void output(int msg)
    {
        if(msg==5) {
            if (totalscore >= 10) {
                output.setText("Exellent you got full scores!");
            }
        }
        else if(msg==6) {
            if (6 < totalscore && totalscore < 10) {
                output.setText("Good job ! ");
            }
        }
        else if(msg==7) {
            if (4 < totalscore && totalscore < 7) {
                output.setText("Not bad !");
            }
        }
        else if(msg==8)
            if(totalscore<=4) {
                {
                    output.setText("Awefull ! You should try more");
                }
            }
    }
    private void onclickoutput()
    {
        buttonout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output(5);
                output(6);
                output(7);
                output(8);
            }
        });
    }
    private void Timing()
    {
        CountDownTimer mycount=new CountDownTimer(7000,1000) {
            @Override
            public void onTick(long remaining) {
                timeremainig.setText("Your remain time :" +remaining/1100);

                if(remaining<=2000)
                {
                    leftbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            return;
                        }
                    });
                    rightbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            return;
                        }
                    });
                    equalbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            return;
                        }
                    });

                    output(5);
                    output(6);
                    output(7);
                    output(8);

                }
            }

            @Override
            public void onFinish() {

                timeremainig.setText("Your time finished! Game over");
            }


        };
        mycount.start();


    }




}
