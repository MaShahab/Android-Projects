package com.example.mah.speedmatch;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.DelayQueue;

public class GameFragment extends Fragment {
    private ConstraintLayout GameContainer;

    private TextView Counter;
    private TextView timeRemaining;
    private TextView playerScore;

    private TextView first;
    private TextView second;

    private Button Both;
    private Button One;
    private Button None;

    private ImageView redTriangle;
    private ImageView blueTriangle;
    private ImageView greenTriangle;
    private ImageView redCircle;
    private ImageView blueCircle;
    private ImageView greenCircle;
    private ImageView redSquare;
    private ImageView blueSquare;
    private ImageView greenSquare;

    private int limitGeneration=0;
    private int limitGeneration2=1;

    private int CountDownStart = 3;
    private String PlayerName;

    private int totalScore=0;

    private void readArguments() {
        PlayerName = getArguments().getString("player_name", null);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        readArguments();
        return inflater.inflate(R.layout.fragment_game, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introduction(view);

        timeCounter();

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

        redTriangle = (ImageView) view.findViewById(R.id.red_triangle);
        blueTriangle = (ImageView) view.findViewById(R.id.blue_triangle);
        greenTriangle = (ImageView) view.findViewById(R.id.green_triangle);
        redCircle = (ImageView) view.findViewById(R.id.red_circle);
        blueCircle = (ImageView) view.findViewById(R.id.blue_circle);
        greenCircle = (ImageView) view.findViewById(R.id.green_circle);
        redSquare = (ImageView) view.findViewById(R.id.red_square);
        blueSquare = (ImageView) view.findViewById(R.id.blue_square);
        greenSquare = (ImageView) view.findViewById(R.id.green_square);


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
        animatorSet.addListener(new AnimatorListenerAdapter() {
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
        CountDownTimer countDownTimer = new CountDownTimer(14000, 1000) {
            @Override
            public void onTick(long remain) {
                timeRemaining.setText("Reamin time is :  " + remain / 1000);
                if(remain%2==0)
                {
                    numberRandom();
                    first.setText(String.valueOf(limitGeneration));
                    shapesRandom();
                    numberRandom2();
                    second.setText(String.valueOf(limitGeneration2));
                    shapesRandom2();
                    buttonClicking();
                }

            }

            @Override
            public void onFinish() {

                timeRemaining.setText("Time is up !");
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

    }private void numberRandom2() {
        Random random2 = new Random();
        limitGeneration2 = random2.nextInt();
        if (limitGeneration2 < 0) {
            limitGeneration2 = limitGeneration2 * -1;
        }
        if (limitGeneration2 > 9) {
            limitGeneration2 = limitGeneration2 % 9;
        }

    }

    private void shapesRandom()

    {
        if (limitGeneration == 0) {
            ObjectAnimator redTriangleAnimator = ObjectAnimator.ofFloat(redTriangle, "translationX", 0, +3000);
            redTriangleAnimator.setDuration(2000);
            redTriangleAnimator.setStartDelay(3000);
            redTriangleAnimator.start();
        } else if (limitGeneration == 1) {
            ObjectAnimator blueTriangleAnimator = ObjectAnimator.ofFloat(blueTriangle, "translationX", 0, +3000);
            blueTriangleAnimator.setDuration(2000);
            blueTriangleAnimator.setStartDelay(3000);
            blueTriangleAnimator.start();
        } else if (limitGeneration == 2) {
            ObjectAnimator greenTriangleAnimator = ObjectAnimator.ofFloat(greenTriangle, "translationX", 0, +3000);
            greenTriangleAnimator.setDuration(2000);
            greenTriangleAnimator.setStartDelay(3000);
            greenTriangleAnimator.start();
        } else if (limitGeneration == 3) {
            ObjectAnimator redCircleAnimator = ObjectAnimator.ofFloat(redCircle, "translationX", 0, +3000);
            redCircleAnimator.setDuration(2000);
            redCircleAnimator.setStartDelay(3000);
            redCircleAnimator.start();
        } else if (limitGeneration == 4) {
            ObjectAnimator blueCircleAnimator = ObjectAnimator.ofFloat(blueCircle, "translationX", 0, +3000);
            blueCircleAnimator.setDuration(2000);
            blueCircleAnimator.setStartDelay(3000);
            blueCircleAnimator.start();
        } else if (limitGeneration == 5) {
            ObjectAnimator greenCircleAnimator = ObjectAnimator.ofFloat(greenCircle, "translationX", 0, +3000);
            greenCircleAnimator.setDuration(2000);
            greenCircleAnimator.setStartDelay(3000);
            greenCircleAnimator.start();
        } else if (limitGeneration == 6) {
            ObjectAnimator redSquareAnimator = ObjectAnimator.ofFloat(redSquare, "translationX", 0, +3000);
            redSquareAnimator.setDuration(2000);
            redSquareAnimator.setStartDelay(3000);
            redSquareAnimator.start();
        } else if (limitGeneration == 7) {
            ObjectAnimator blueSquareAnimator = ObjectAnimator.ofFloat(blueSquare, "translationX", 0, +3000);
            blueSquareAnimator.setDuration(2000);
            blueSquareAnimator.setStartDelay(3000);
            blueSquareAnimator.start();
        } else if (limitGeneration == 8) {
            ObjectAnimator greenSquareAnimator = ObjectAnimator.ofFloat(greenSquare, "translationX", 0, +3000);
            greenSquareAnimator.setDuration(2000);
            greenSquareAnimator.setStartDelay(3000);
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
                if(limitGeneration==0 && limitGeneration2==0 || limitGeneration==1 && limitGeneration2==1 ||
                        limitGeneration==2 && limitGeneration2==2 || limitGeneration==3 && limitGeneration2==3 ||
                        limitGeneration==4 && limitGeneration2==4 || limitGeneration==5 && limitGeneration2==5 ||
                        limitGeneration==6 && limitGeneration2==6 || limitGeneration==7 && limitGeneration2==7 ||
                        limitGeneration==8 && limitGeneration2==8)
                {
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                }
            }
        });
        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(limitGeneration==0 && limitGeneration2==1 || limitGeneration==0 && limitGeneration2==2 ||
                        limitGeneration==1 && limitGeneration2==0 || limitGeneration==2 && limitGeneration2==0 ||
                        limitGeneration==0 && limitGeneration2==3 || limitGeneration==0 && limitGeneration2==6 ||
                        limitGeneration==3 && limitGeneration2==0 || limitGeneration==6 && limitGeneration2==0 ||
                        limitGeneration==1 && limitGeneration2==2 || limitGeneration==1 && limitGeneration2==5 ||
                        limitGeneration==2 && limitGeneration2==1 || limitGeneration==5 && limitGeneration2==1 ||
                        limitGeneration==1 && limitGeneration2==7 || limitGeneration==2 && limitGeneration2==4 ||
                        limitGeneration==7 && limitGeneration2==1 || limitGeneration==4 && limitGeneration2==2 ||
                        limitGeneration==2 && limitGeneration2==8 || limitGeneration==3 && limitGeneration2==4 ||
                        limitGeneration==8 && limitGeneration2==2 || limitGeneration==4 && limitGeneration2==3 ||
                        limitGeneration==3 && limitGeneration2==5 || limitGeneration==3 && limitGeneration2==6 ||
                        limitGeneration==5 && limitGeneration2==3 || limitGeneration==6 && limitGeneration2==3 ||
                        limitGeneration==4 && limitGeneration2==5 || limitGeneration==4 && limitGeneration2==8 ||
                        limitGeneration==5 && limitGeneration2==4 || limitGeneration==8 && limitGeneration2==4 ||
                        limitGeneration==5 && limitGeneration2==7 || limitGeneration==6 && limitGeneration2==7 ||
                        limitGeneration==7 && limitGeneration2==5 || limitGeneration==7 && limitGeneration2==6 ||
                        limitGeneration==6 && limitGeneration2==8 || limitGeneration==7 && limitGeneration2==7 ||
                        limitGeneration==8 && limitGeneration2==6 )
                {
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                }
            }
        });
        None.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(limitGeneration==0 && limitGeneration2==4 ||limitGeneration==0 && limitGeneration2==5 ||
                        limitGeneration==4 && limitGeneration2==0 ||limitGeneration==5 && limitGeneration2==0 ||
                        limitGeneration==0 && limitGeneration2==7 ||limitGeneration==0 && limitGeneration2==8 ||
                        limitGeneration==7 && limitGeneration2==0 ||limitGeneration==8 && limitGeneration2==0 ||
                        limitGeneration==2 && limitGeneration2==5 ||limitGeneration==1 && limitGeneration2==3 ||
                        limitGeneration==5 && limitGeneration2==2 ||limitGeneration==3 && limitGeneration2==1 ||
                        limitGeneration==1 && limitGeneration2==4 ||limitGeneration==1 && limitGeneration2==6 ||
                        limitGeneration==4 && limitGeneration2==1 ||limitGeneration==6 && limitGeneration2==1 ||
                        limitGeneration==1 && limitGeneration2==8 ||limitGeneration==2 && limitGeneration2==3 ||
                        limitGeneration==8 && limitGeneration2==1 ||limitGeneration==3 && limitGeneration2==2 ||
                        limitGeneration==2 && limitGeneration2==6 ||limitGeneration==2 && limitGeneration2==7 ||
                        limitGeneration==6 && limitGeneration2==2 ||limitGeneration==7 && limitGeneration2==2 ||
                        limitGeneration==3 && limitGeneration2==7 ||limitGeneration==3 && limitGeneration2==8 ||
                        limitGeneration==7 && limitGeneration2==3 ||limitGeneration==8 && limitGeneration2==3 ||
                        limitGeneration==4 && limitGeneration2==6 ||limitGeneration==4 && limitGeneration2==7 ||
                        limitGeneration==6 && limitGeneration2==4 ||limitGeneration==7 && limitGeneration2==4 ||
                        limitGeneration==5 && limitGeneration2==6 ||limitGeneration==5 && limitGeneration2==8 ||
                        limitGeneration==6 && limitGeneration2==5 ||limitGeneration==8 && limitGeneration2==5 )
                {
                    playerScore.setText("Your score is : " + String.valueOf(totalScore++));
                }



            }
        });
    }

    private void updateHightScore()
    {
        Ranklist ranklist = MyPreferenceManager.getInstance(getActivity()).getRanklist();
        Users NewUser= new Users();
        NewUser.setName(PlayerName);
        NewUser.setScore(totalScore);
        ranklist.addUsertoList(NewUser);
        MyPreferenceManager.getInstance(getActivity()).putRanklist(ranklist);
    }
}
