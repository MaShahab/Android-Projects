package com.example.mah.gamespack.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mah.gamespack.R;
import com.example.mah.gamespack.SpeedMatchPreferenceManager;
import com.example.mah.gamespack.models.Ranklist;
import com.example.mah.gamespack.models.Users;

import java.util.Random;

public class SpeedMatchGameFragment extends Fragment
{
    private ConstraintLayout GameContainer;

    private TextView Counter;
    private TextView timeRemaining;
    private TextView playerScore;

    private TextView first;
    private TextView second;

    private Button Both;
    private Button One;
    private Button None;
    private Button Both2;
    private Button One2;
    private Button None2;

    private ImageView redTriangle;
    private ImageView blueTriangle;
    private ImageView greenTriangle;
    private ImageView redCircle;
    private ImageView blueCircle;
    private ImageView greenCircle;
    private ImageView redSquare;
    private ImageView blueSquare;
    private ImageView greenSquare;

    private ImageView greenTick;
    private ImageView redMul;

    private int limitGeneration=0;
    private int limitGeneration2=0;
    private int limitGeneration3=0;

    private int CountDownStart = 3;
    private String PlayerName;

    private int totalScore=1;

    private void readArguments() {
        PlayerName = getArguments().getString("player_name", null);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        readArguments();
        return inflater.inflate( R.layout.speed_match_game_fragment , container ,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);

        Both.setClickable ( false );
        One.setClickable ( false );
        None.setClickable ( false );

        timeCounter();

        numberRandom2 ();
        shapesRandom2 ();

        numberRandom3 ();
        shapesRandom3 ();

        setTimeRemaining();
    }

    private void introduction(View view) {
        Counter = (TextView) view.findViewById(R.id.counter);
        timeRemaining = (TextView) view.findViewById(R.id.time_bar);
        playerScore = (TextView) view.findViewById(R.id.game_score);

        first = (TextView) view.findViewById(R.id.first_text_view);
        second = (TextView) view.findViewById(R.id.second_text_view);

        GameContainer = (ConstraintLayout) view.findViewById(R.id.game_container);

        Both = (Button) view.findViewById(R.id.both_same);
        One = (Button) view.findViewById(R.id.one_same);
        None = (Button) view.findViewById(R.id.none_same);

        Both2 = (Button) view.findViewById(R.id.both_same2);
        One2 = (Button) view.findViewById(R.id.one_same2);
        None2 = (Button) view.findViewById(R.id.none_same2);

        redTriangle = (ImageView) view.findViewById(R.id.red_triangle);
        blueTriangle = (ImageView) view.findViewById(R.id.blue_triangle);
        greenTriangle = (ImageView) view.findViewById(R.id.green_triangle);
        redCircle = (ImageView) view.findViewById(R.id.red_circle);
        blueCircle = (ImageView) view.findViewById(R.id.blue_circle);
        greenCircle = (ImageView) view.findViewById(R.id.green_circle);
        redSquare = (ImageView) view.findViewById(R.id.red_square);
        blueSquare = (ImageView) view.findViewById(R.id.blue_square);
        greenSquare = (ImageView) view.findViewById(R.id.green_square);

        greenTick=(ImageView) view.findViewById ( R.id.tick );
        redMul=(ImageView) view.findViewById ( R.id.mul );

    }

    private void timeCounter() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(Counter, "scaleX", 0f, 6f);
        scaleX.setDuration(1000);
        scaleX.setStartDelay(-2000);
        scaleX.start();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(Counter, "scaleY", 0f, 6f);
        scaleY.setDuration(1000);
        scaleY.start();
        ObjectAnimator Alpha = ObjectAnimator.ofFloat(Counter, "Alpha", 1f, 0f);
        Alpha.setDuration(1000);
        Alpha.start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, Alpha);
        animatorSet.addListener(new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (CountDownStart == 0) {
                    GameContainer.setVisibility(View.VISIBLE);
                } else timeCounter();

            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Counter.setText(String.valueOf(CountDownStart));
                CountDownStart--;
            }
        });

        animatorSet.start();

    }

    private void setTimeRemaining() {
        CountDownTimer countDownTimer = new CountDownTimer(24000, 1000) {
            @Override
            public void onTick(long remain) {
                timeRemaining.setText("Remain time is :  " + remain / 1000);
                if(remain%2==0)
                {
//                    numberRandom();
//                    first.setText(String.valueOf(limitGeneration));
//                    shapesRandom();
//                    numberRandom2();
//                    second.setText(String.valueOf(limitGeneration2));
//                    shapesRandom2();
                    buttonClicking2 ();
//                    buttonClicking();
                }

            }

            @Override
            public void onFinish() {

                timeRemaining.setText("Time is up !");

                One2.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        return;
                    }
                } );

                Both2.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        return;
                    }
                } );

                None2.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        return;
                    }
                } );

                One.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        return;
                    }
                });
                Both.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        return;
                    }
                });
                None.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        return;
                    }
                });
                updateHightScore();

            }
        };
        countDownTimer.start();
    }

    private void numberRandom() {
        Random random = new Random();
        limitGeneration = random.nextInt();
        if (limitGeneration < 0) {
            limitGeneration = limitGeneration * -1;
        }
        if (limitGeneration > 9) {
            limitGeneration = limitGeneration % 9;
        }

    }

    private void numberRandom2()
   {
    Random random2 = new Random();
    limitGeneration2 = random2.nextInt();
    if (limitGeneration2 < 0) {
        limitGeneration2 = limitGeneration2 * -1;
    }
    if (limitGeneration2 > 9) {
        limitGeneration2 = limitGeneration2 % 9;
    }
  }

  private void numberRandom3()
   {
    Random random3 = new Random();
    limitGeneration3 = random3.nextInt();
    if (limitGeneration3 < 0) {
        limitGeneration3 = limitGeneration3 * -1;
    }
    if (limitGeneration3 > 9) {
        limitGeneration3 = limitGeneration3 % 9;
    }
  }

    private void shapesRandom3()
    {
        if (limitGeneration3 == 0) {
            ObjectAnimator redTriangleAnimator = ObjectAnimator.ofFloat(redTriangle, "translationX", 0, +3000);
            redTriangleAnimator.setDuration(2000);
            redTriangleAnimator.setStartDelay(5000);
            redTriangleAnimator.start();

        } else if (limitGeneration3 == 1) {
            ObjectAnimator blueTriangleAnimator = ObjectAnimator.ofFloat(blueTriangle, "translationX", 0, +3000);
            blueTriangleAnimator.setDuration(2000);
            blueTriangleAnimator.setStartDelay(5000);
            blueTriangleAnimator.start();
        } else if (limitGeneration3 == 2) {
            ObjectAnimator greenTriangleAnimator = ObjectAnimator.ofFloat(greenTriangle, "translationX", 0, +3000);
            greenTriangleAnimator.setDuration(2000);
            greenTriangleAnimator.setStartDelay(5000);
            greenTriangleAnimator.start();
        } else if (limitGeneration3 == 3) {
            ObjectAnimator redCircleAnimator = ObjectAnimator.ofFloat(redCircle, "translationX", 0, +3000);
            redCircleAnimator.setDuration(2000);
            redCircleAnimator.setStartDelay(5000);
            redCircleAnimator.start();
        } else if (limitGeneration3 == 4) {
            ObjectAnimator blueCircleAnimator = ObjectAnimator.ofFloat(blueCircle, "translationX", 0, +3000);
            blueCircleAnimator.setDuration(2000);
            blueCircleAnimator.setStartDelay(5000);
            blueCircleAnimator.start();
        } else if (limitGeneration3 == 5) {
            ObjectAnimator greenCircleAnimator = ObjectAnimator.ofFloat(greenCircle, "translationX", 0, +3000);
            greenCircleAnimator.setDuration(2000);
            greenCircleAnimator.setStartDelay(5000);
            greenCircleAnimator.start();
        } else if (limitGeneration3 == 6) {
            ObjectAnimator redSquareAnimator = ObjectAnimator.ofFloat(redSquare, "translationX", 0, +3000);
            redSquareAnimator.setDuration(2000);
            redSquareAnimator.setStartDelay(5000);
            redSquareAnimator.start();
        } else if (limitGeneration3 == 7) {
            ObjectAnimator blueSquareAnimator = ObjectAnimator.ofFloat(blueSquare, "translationX", 0, +3000);
            blueSquareAnimator.setDuration(2000);
            blueSquareAnimator.setStartDelay(5000);
            blueSquareAnimator.start();
        } else if (limitGeneration3 == 8) {
            ObjectAnimator greenSquareAnimator = ObjectAnimator.ofFloat(greenSquare, "translationX", 0, +3000);
            greenSquareAnimator.setDuration(2000);
            greenSquareAnimator.setStartDelay(5000);
            greenSquareAnimator.start();
        }
    }

    private void shapesRandom()

    {
        if (limitGeneration == 0) {
            ObjectAnimator redTriangleAnimator = ObjectAnimator.ofFloat(redTriangle, "translationX", 0, +3000);
            redTriangleAnimator.setDuration(2000);
//            redTriangleAnimator.setStartDelay(3000);
            redTriangleAnimator.start();
        } else if (limitGeneration == 1) {
            ObjectAnimator blueTriangleAnimator = ObjectAnimator.ofFloat(blueTriangle, "translationX", 0, +3000);
            blueTriangleAnimator.setDuration(2000);
//            blueTriangleAnimator.setStartDelay(3000);
            blueTriangleAnimator.start();
        } else if (limitGeneration == 2) {
            ObjectAnimator greenTriangleAnimator = ObjectAnimator.ofFloat(greenTriangle, "translationX", 0, +3000);
            greenTriangleAnimator.setDuration(2000);
//            greenTriangleAnimator.setStartDelay(3000);
            greenTriangleAnimator.start();
        } else if (limitGeneration == 3) {
            ObjectAnimator redCircleAnimator = ObjectAnimator.ofFloat(redCircle, "translationX", 0, +3000);
            redCircleAnimator.setDuration(2000);
//            redCircleAnimator.setStartDelay(3000);
            redCircleAnimator.start();
        } else if (limitGeneration == 4) {
            ObjectAnimator blueCircleAnimator = ObjectAnimator.ofFloat(blueCircle, "translationX", 0, +3000);
            blueCircleAnimator.setDuration(2000);
//            blueCircleAnimator.setStartDelay(3000);
            blueCircleAnimator.start();
        } else if (limitGeneration == 5) {
            ObjectAnimator greenCircleAnimator = ObjectAnimator.ofFloat(greenCircle, "translationX", 0, +3000);
            greenCircleAnimator.setDuration(2000);
//            greenCircleAnimator.setStartDelay(3000);
            greenCircleAnimator.start();
        } else if (limitGeneration == 6) {
            ObjectAnimator redSquareAnimator = ObjectAnimator.ofFloat(redSquare, "translationX", 0, +3000);
            redSquareAnimator.setDuration(2000);
//            redSquareAnimator.setStartDelay(3000);
            redSquareAnimator.start();
        } else if (limitGeneration == 7) {
            ObjectAnimator blueSquareAnimator = ObjectAnimator.ofFloat(blueSquare, "translationX", 0, +3000);
            blueSquareAnimator.setDuration(2000);
//            blueSquareAnimator.setStartDelay(3000);
            blueSquareAnimator.start();
        } else if (limitGeneration == 8) {
            ObjectAnimator greenSquareAnimator = ObjectAnimator.ofFloat(greenSquare, "translationX", 0, +3000);
            greenSquareAnimator.setDuration(2000);
//            greenSquareAnimator.setStartDelay(3000);
            greenSquareAnimator.start();
        }
    }
    private void shapesRandom2()

    {
        if (limitGeneration2 == 0) {
            ObjectAnimator redTriangleAnimator = ObjectAnimator.ofFloat(redTriangle, "translationX", 0, +3000);
            redTriangleAnimator.setDuration(2000);
            redTriangleAnimator.setStartDelay(3000);
            redTriangleAnimator.start();
        } else if (limitGeneration2 == 1) {
            ObjectAnimator blueTriangleAnimator = ObjectAnimator.ofFloat(blueTriangle, "translationX", 0, +3000);
            blueTriangleAnimator.setDuration(2000);
            blueTriangleAnimator.setStartDelay(3000);
            blueTriangleAnimator.start();
        } else if (limitGeneration2 == 2) {
            ObjectAnimator greenTriangleAnimator = ObjectAnimator.ofFloat(greenTriangle, "translationX", 0, +3000);
            greenTriangleAnimator.setDuration(2000);
            greenTriangleAnimator.setStartDelay(3000);
            greenTriangleAnimator.start();
        } else if (limitGeneration2 == 3) {
            ObjectAnimator redCircleAnimator = ObjectAnimator.ofFloat(redCircle, "translationX", 0, +3000);
            redCircleAnimator.setDuration(2000);
            redCircleAnimator.setStartDelay(3000);
            redCircleAnimator.start();
        } else if (limitGeneration2 == 4) {
            ObjectAnimator blueCircleAnimator = ObjectAnimator.ofFloat(blueCircle, "translationX", 0, +3000);
            blueCircleAnimator.setDuration(2000);
            blueCircleAnimator.setStartDelay(3000);
            blueCircleAnimator.start();
        } else if (limitGeneration2 == 5) {
            ObjectAnimator greenCircleAnimator = ObjectAnimator.ofFloat(greenCircle, "translationX", 0, +3000);
            greenCircleAnimator.setDuration(2000);
            greenCircleAnimator.setStartDelay(3000);
            greenCircleAnimator.start();
        } else if (limitGeneration2 == 6) {
            ObjectAnimator redSquareAnimator = ObjectAnimator.ofFloat(redSquare, "translationX", 0, +3000);
            redSquareAnimator.setDuration(2000);
            redSquareAnimator.setStartDelay(3000);
            redSquareAnimator.start();
        } else if (limitGeneration2 == 7) {
            ObjectAnimator blueSquareAnimator = ObjectAnimator.ofFloat(blueSquare, "translationX", 0, +3000);
            blueSquareAnimator.setDuration(2000);
            blueSquareAnimator.setStartDelay(3000);
            blueSquareAnimator.start();
        } else if (limitGeneration2 == 8) {
            ObjectAnimator greenSquareAnimator = ObjectAnimator.ofFloat(greenSquare, "translationX", 0, +3000);
            greenSquareAnimator.setDuration(2000);
            greenSquareAnimator.setStartDelay(3000);
            greenSquareAnimator.start();
        }
    }




//        switch(limitGeneration)
//    {
//        case 0:
//            ObjectAnimator redTriangleAnimator = ObjectAnimator.ofFloat(redTriangle, "translationX", 0, +2000);
//            redTriangleAnimator.setDuration(2000);
//            redTriangleAnimator.setStartDelay(3000);
//            redTriangleAnimator.start();
//        case 1:
//            ObjectAnimator blueTriangleAnimator = ObjectAnimator.ofFloat(blueTriangle, "translationX", 0, +2000);
//            blueTriangleAnimator.setDuration(2000);
//            blueTriangleAnimator.setStartDelay(3000);
//            blueTriangleAnimator.start();
//        case 2:
//            ObjectAnimator greenTriangleAnimator = ObjectAnimator.ofFloat(greenTriangle, "translationX", 0, +2000);
//            greenTriangleAnimator.setDuration(2000);
//            greenTriangleAnimator.setStartDelay(3000);
//            greenTriangleAnimator.start();
//        case 3:
//            ObjectAnimator redCircleAnimator = ObjectAnimator.ofFloat(redCircle, "translationX", 0, +2000);
//            redCircleAnimator.setDuration(2000);
//            redCircleAnimator.setStartDelay(3000);
//            redCircleAnimator.start();
//        case 4:
//            ObjectAnimator blueCircleAnimator = ObjectAnimator.ofFloat(blueCircle, "translationX", 0, +2000);
//            blueCircleAnimator.setDuration(2000);
//            blueCircleAnimator.setStartDelay(3000);
//            blueCircleAnimator.start();
//        case 5:
//            ObjectAnimator greenCircleAnimator = ObjectAnimator.ofFloat(greenCircle, "translationX", 0, +2000);
//            greenCircleAnimator.setDuration(2000);
//            greenCircleAnimator.setStartDelay(3000);
//            greenCircleAnimator.start();
//        case 6:
//            ObjectAnimator redSquareAnimator = ObjectAnimator.ofFloat(redSquare, "translationX", 0, +2000);
//            redSquareAnimator.setDuration(2000);
//            redSquareAnimator.setStartDelay(3000);
//            redSquareAnimator.start();
//        case 7:
//            ObjectAnimator blueSquareAnimator = ObjectAnimator.ofFloat(blueSquare, "translationX", 0, +2000);
//            blueSquareAnimator.setDuration(2000);
//            blueSquareAnimator.setStartDelay(3000);
//            blueSquareAnimator.start();
//        case 8:
//            ObjectAnimator greenSquareAnimator = ObjectAnimator.ofFloat(greenSquare, "translationX", 0, +2000);
//            greenSquareAnimator.setDuration(2000);
//            greenSquareAnimator.setStartDelay(3000);
//            greenSquareAnimator.start();
//    }




    private void buttonClicking()
    {

        Both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(limitGeneration3==0 && limitGeneration==0 || limitGeneration3==1 && limitGeneration==1 ||
                        limitGeneration3==2 && limitGeneration==2 || limitGeneration3==3 && limitGeneration==3 ||
                        limitGeneration3==4 && limitGeneration==4 || limitGeneration3==5 && limitGeneration==5 ||
                        limitGeneration3==6 && limitGeneration==6 || limitGeneration3==7 && limitGeneration==7 ||
                        limitGeneration3==8 && limitGeneration==8)
                {
                    greenTick.setVisibility ( View.VISIBLE );
                    animateTick ();
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                    limitGeneration3=0;
                    limitGeneration3=limitGeneration;
                    numberRandom ();
                    shapesRandom ();
                }
//                limitGeneration3=0;
//                limitGeneration3=limitGeneration;
//                numberRandom ();
//                redMul.setVisibility ( View.VISIBLE );
//                animateMul ();
                else {
                    redMul.setVisibility ( View.VISIBLE );
                    animateMul();
                    shapesRandom ();
                }
            }
        });


        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Both2.setVisibility ( View.VISIBLE );
//                One2.setVisibility ( View.VISIBLE );
//                None2.setVisibility ( View.VISIBLE );
//
//                Both.setVisibility(View.INVISIBLE);
//                One.setVisibility ( View.INVISIBLE );
//                None.setVisibility ( View.INVISIBLE );

                if(limitGeneration3==0 && limitGeneration==1 || limitGeneration3==0 && limitGeneration==2 ||
                        limitGeneration3==1 && limitGeneration==0 || limitGeneration3==2 && limitGeneration==0 ||
                        limitGeneration3==0 && limitGeneration==3 || limitGeneration3==0 && limitGeneration==6 ||
                        limitGeneration3==3 && limitGeneration==0 || limitGeneration3==6 && limitGeneration==0 ||
                        limitGeneration3==1 && limitGeneration==2 || limitGeneration3==2 && limitGeneration==1 ||
                        limitGeneration3==1 && limitGeneration==7 || limitGeneration3==7 && limitGeneration==1 ||
                        limitGeneration3==2 && limitGeneration==8 || limitGeneration3==3 && limitGeneration==4 ||
                        limitGeneration3==8 && limitGeneration==2 || limitGeneration3==4 && limitGeneration==3 ||
                        limitGeneration3==3 && limitGeneration==5 || limitGeneration3==3 && limitGeneration==6 ||
                        limitGeneration3==5 && limitGeneration==3 || limitGeneration3==6 && limitGeneration==3 ||
                        limitGeneration3==4 && limitGeneration==5 || limitGeneration3==5 && limitGeneration==4 ||
                        limitGeneration3==6 && limitGeneration==7 || limitGeneration3==7 && limitGeneration==6 ||
                        limitGeneration3==6 && limitGeneration==8 || limitGeneration3==2 && limitGeneration==7 ||
                        limitGeneration3==8 && limitGeneration==6 || limitGeneration3==7 && limitGeneration==2 ||
                        limitGeneration3==8 && limitGeneration==5 || limitGeneration3==5 && limitGeneration==8 ||
                        limitGeneration3==7 && limitGeneration==8 || limitGeneration3==8 && limitGeneration==7 ||
                        limitGeneration3==1 && limitGeneration==4 || limitGeneration3==4 && limitGeneration==1 ||
                        limitGeneration3==7 && limitGeneration==4 || limitGeneration3==4 && limitGeneration==7 ||
                        limitGeneration3==2 && limitGeneration==5 || limitGeneration3==5 && limitGeneration==2 )
                {
                    greenTick.setVisibility ( View.VISIBLE );
                    animateTick ();
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                    limitGeneration3=0;
                    limitGeneration3=limitGeneration;
                    numberRandom ();
                    shapesRandom ();
                }
//                limitGeneration3=0;
//                limitGeneration3=limitGeneration;
//                numberRandom ();
//                redMul.setVisibility ( View.VISIBLE );
//                animateMul ();
                else {
                    redMul.setVisibility ( View.VISIBLE );
                    animateMul();
                    shapesRandom ();
                }

            }
        });
        None.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Both2.setVisibility ( View.VISIBLE );
//                One2.setVisibility ( View.VISIBLE );
//                None2.setVisibility ( View.VISIBLE );
//
//                Both.setVisibility(View.INVISIBLE);
//                One.setVisibility ( View.INVISIBLE );
//                None.setVisibility ( View.INVISIBLE );

                if(limitGeneration3==0 && limitGeneration==4 ||limitGeneration3==0 && limitGeneration==5 ||
                        limitGeneration3==4 && limitGeneration==0 ||limitGeneration3==5 && limitGeneration==0 ||
                        limitGeneration3==0 && limitGeneration==7 ||limitGeneration3==0 && limitGeneration==8 ||
                        limitGeneration3==7 && limitGeneration==0 ||limitGeneration3==8 && limitGeneration==0 ||
                        limitGeneration3==1 && limitGeneration==3 ||limitGeneration3==3 && limitGeneration==1 ||
                        limitGeneration3==1 && limitGeneration==6 ||limitGeneration3==6 && limitGeneration==1 ||
                        limitGeneration3==1 && limitGeneration==8 ||limitGeneration3==2 && limitGeneration==3 ||
                        limitGeneration3==8 && limitGeneration==1 ||limitGeneration3==3 && limitGeneration==2 ||
                        limitGeneration3==2 && limitGeneration==6 ||limitGeneration3==2 && limitGeneration==7 ||
                        limitGeneration3==6 && limitGeneration==2 ||limitGeneration3==7 && limitGeneration==2 ||
                        limitGeneration3==3 && limitGeneration==7 ||limitGeneration3==3 && limitGeneration==8 ||
                        limitGeneration3==7 && limitGeneration==3 ||limitGeneration3==8 && limitGeneration==3 ||
                        limitGeneration3==4 && limitGeneration==6 ||limitGeneration3==6 && limitGeneration==4 ||
                        limitGeneration3==5 && limitGeneration==6 ||limitGeneration3==5 && limitGeneration==1 ||
                        limitGeneration3==6 && limitGeneration==5 ||limitGeneration3==1 && limitGeneration==5 ||
                        limitGeneration3==2 && limitGeneration==4 ||limitGeneration3==4 && limitGeneration==2 ||
                        limitGeneration3==8 && limitGeneration==4 ||limitGeneration3==4 && limitGeneration==8 ||
                        limitGeneration3==7 && limitGeneration==5 ||limitGeneration3==5 && limitGeneration==7 )
                {
                    greenTick.setVisibility ( View.VISIBLE );
                    animateTick ();
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                    limitGeneration3=0;
                    limitGeneration3=limitGeneration;
                    numberRandom ();
                    shapesRandom ();
                }
//                limitGeneration3=0;
//                limitGeneration3=limitGeneration;
//                numberRandom ();
//                redMul.setVisibility ( View.VISIBLE );
//                animateMul ();
                else {
                    shapesRandom ();
                    redMul.setVisibility ( View.VISIBLE );
                    animateMul();
                }

            }
        });
    }


    private void buttonClicking2()
    {
        Both2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Both2.setVisibility ( View.INVISIBLE );
                One2.setVisibility ( View.INVISIBLE );
                None2.setVisibility ( View.INVISIBLE );

                Both2.setClickable ( false );
                One2.setClickable ( false );
                None2.setClickable ( false );

                Both.setClickable ( true );
                One.setClickable ( true );
                None.setClickable ( true );

                Both.setVisibility ( View.VISIBLE );
                One.setVisibility ( View.VISIBLE );
                None.setVisibility ( View.VISIBLE );

                if(limitGeneration3==0 && limitGeneration2==0 || limitGeneration3==1 && limitGeneration2==1 ||
                        limitGeneration3==2 && limitGeneration2==2 || limitGeneration3==3 && limitGeneration2==3 ||
                        limitGeneration3==4 && limitGeneration2==4 || limitGeneration3==5 && limitGeneration2==5 ||
                        limitGeneration3==6 && limitGeneration2==6 || limitGeneration3==7 && limitGeneration2==7 ||
                        limitGeneration3==8 && limitGeneration2==8)
                {
                    greenTick.setVisibility ( View.VISIBLE );
                    animateTick ();
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                    numberRandom ();
                    shapesRandom ();
//                    buttonClicking();
                }
                else {
                redMul.setVisibility ( View.VISIBLE );
                animateMul ();}
            }
        } );


        One2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Both2.setVisibility ( View.INVISIBLE );
                One2.setVisibility ( View.INVISIBLE );
                None2.setVisibility ( View.INVISIBLE );

                Both2.setClickable ( false );
                One2.setClickable ( false );
                None2.setClickable ( false );

                Both.setClickable ( true );
                One.setClickable ( true );
                None.setClickable ( true );

                Both.setVisibility ( View.VISIBLE );
                One.setVisibility ( View.VISIBLE );
                None.setVisibility ( View.VISIBLE );


                if(limitGeneration3==0 && limitGeneration2==1 || limitGeneration3==0 && limitGeneration2==2 ||
                        limitGeneration3==1 && limitGeneration2==0 || limitGeneration3==2 && limitGeneration2==0 ||
                        limitGeneration3==0 && limitGeneration2==3 || limitGeneration3==0 && limitGeneration2==6 ||
                        limitGeneration3==3 && limitGeneration2==0 || limitGeneration3==6 && limitGeneration2==0 ||
                        limitGeneration3==1 && limitGeneration2==2 || limitGeneration3==2 && limitGeneration2==1 ||
                        limitGeneration3==1 && limitGeneration2==7 || limitGeneration3==7 && limitGeneration2==1 ||
                        limitGeneration3==2 && limitGeneration2==8 || limitGeneration3==3 && limitGeneration2==4 ||
                        limitGeneration3==8 && limitGeneration2==2 || limitGeneration3==4 && limitGeneration2==3 ||
                        limitGeneration3==3 && limitGeneration2==5 || limitGeneration3==3 && limitGeneration2==6 ||
                        limitGeneration3==5 && limitGeneration2==3 || limitGeneration3==6 && limitGeneration2==3 ||
                        limitGeneration3==4 && limitGeneration2==5 || limitGeneration3==5 && limitGeneration2==4 ||
                        limitGeneration3==6 && limitGeneration2==7 || limitGeneration3==7 && limitGeneration2==6 ||
                        limitGeneration3==6 && limitGeneration2==8 || limitGeneration3==2 && limitGeneration2==7 ||
                        limitGeneration3==8 && limitGeneration2==6 || limitGeneration3==7 && limitGeneration2==2 ||
                        limitGeneration3==8 && limitGeneration2==5 || limitGeneration3==5 && limitGeneration2==8 ||
                        limitGeneration3==7 && limitGeneration2==8 || limitGeneration3==8 && limitGeneration2==7 ||
                        limitGeneration3==1 && limitGeneration2==4 || limitGeneration3==4 && limitGeneration2==1 ||
                        limitGeneration3==7 && limitGeneration2==4 || limitGeneration3==4 && limitGeneration2==7 ||
                        limitGeneration3==2 && limitGeneration2==5 || limitGeneration3==5 && limitGeneration2==2 )
                {
                    greenTick.setVisibility ( View.VISIBLE );
                    animateTick ();
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                    numberRandom ();
                    shapesRandom ();
//                    buttonClicking();
                }
                else {
                    redMul.setVisibility ( View.VISIBLE );
                    animateMul ();
                }
            }
        } );

        None2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Both2.setVisibility ( View.INVISIBLE );
                One2.setVisibility ( View.INVISIBLE );
                None2.setVisibility ( View.INVISIBLE );

                Both2.setClickable ( false );
                One2.setClickable ( false );
                None2.setClickable ( false );

                Both.setClickable ( true );
                One.setClickable ( true );
                None.setClickable ( true );

                Both.setVisibility ( View.VISIBLE );
                One.setVisibility ( View.VISIBLE );
                None.setVisibility ( View.VISIBLE );


                if(limitGeneration3==0 && limitGeneration2==4 ||limitGeneration3==0 && limitGeneration2==5 ||
                        limitGeneration3==4 && limitGeneration2==0 ||limitGeneration3==5 && limitGeneration2==0 ||
                        limitGeneration3==0 && limitGeneration2==7 ||limitGeneration3==0 && limitGeneration2==8 ||
                        limitGeneration3==7 && limitGeneration2==0 ||limitGeneration3==8 && limitGeneration2==0 ||
                        limitGeneration3==1 && limitGeneration2==3 ||limitGeneration3==3 && limitGeneration2==1 ||
                        limitGeneration3==1 && limitGeneration2==6 ||limitGeneration3==6 && limitGeneration2==1 ||
                        limitGeneration3==1 && limitGeneration2==8 ||limitGeneration3==2 && limitGeneration2==3 ||
                        limitGeneration3==8 && limitGeneration2==1 ||limitGeneration3==3 && limitGeneration2==2 ||
                        limitGeneration3==2 && limitGeneration2==6 ||limitGeneration3==2 && limitGeneration2==7 ||
                        limitGeneration3==6 && limitGeneration2==2 ||limitGeneration3==7 && limitGeneration2==2 ||
                        limitGeneration3==3 && limitGeneration2==7 ||limitGeneration3==3 && limitGeneration2==8 ||
                        limitGeneration3==7 && limitGeneration2==3 ||limitGeneration3==8 && limitGeneration2==3 ||
                        limitGeneration3==4 && limitGeneration2==6 ||limitGeneration3==6 && limitGeneration2==4 ||
                        limitGeneration3==5 && limitGeneration2==6 ||limitGeneration3==6 && limitGeneration2==5 ||
                        limitGeneration3==4 && limitGeneration2==2 ||limitGeneration3==2 && limitGeneration2==4 ||
                        limitGeneration3==8 && limitGeneration2==4 ||limitGeneration3==4 && limitGeneration2==8 ||
                        limitGeneration3==1 && limitGeneration2==5 ||limitGeneration3==5 && limitGeneration2==1 ||
                        limitGeneration3==7 && limitGeneration2==5 ||limitGeneration3==5 && limitGeneration2==7)
                {
                    greenTick.setVisibility ( View.VISIBLE );
                    animateTick ();
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                    numberRandom ();
                    shapesRandom ();
//                    buttonClicking();
                }
                else {
                    redMul.setVisibility ( View.VISIBLE );
                    animateMul ();
                }
            }
        } );

        buttonClicking ();

    }


    private void updateHightScore()
    {
        Ranklist ranklist = SpeedMatchPreferenceManager.getInstance ( getActivity () ).getRankList ();
        Users NewUser= new Users();
        NewUser.setName(PlayerName);
        NewUser.setScore(totalScore);
        ranklist.addUsertoList(NewUser);
        SpeedMatchPreferenceManager.getInstance(getActivity()).putRanklist(ranklist);
    }

    private void animateTick()
    {
        ObjectAnimator deltaxAnimator = ObjectAnimator.ofFloat(greenTick,"scaleX" ,1f,5f);
        deltaxAnimator.setDuration(1000);
        deltaxAnimator.start ();
        ObjectAnimator deltayAnimator = ObjectAnimator.ofFloat(greenTick,"scaleY" ,1f,5f);
        deltayAnimator.setDuration(1000);
        deltayAnimator.start ();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(greenTick,"Alpha" ,0f,1f,0f);
        alpha.setDuration(1000);
        alpha.start ();
    }

    private void animateMul()
    {
        ObjectAnimator deltaxAnimator1 = ObjectAnimator.ofFloat(redMul,"scaleX" ,1f,5f);
        deltaxAnimator1.setDuration(1000);
        deltaxAnimator1.start ();
        ObjectAnimator deltayAnimator1 = ObjectAnimator.ofFloat(redMul,"scaleY" ,1f,5f);
        deltayAnimator1.setDuration(1000);
        deltayAnimator1.start ();
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(redMul,"Alpha" ,0f,1f,0f);
        alpha1.setDuration(1000);
        alpha1.start ();
    }

}
