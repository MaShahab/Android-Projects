package com.example.mah.whichoneislarger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameFragment extends Fragment
{
    private TextView score;
    private TextView level;
    private TextView timeremainig;
    private TextView output;

    private Button leftbutton;
    private Button rightbutton;
    private Button equalbutton;
    private Button buttonout;

    private LinearLayout GameContainer;
    private TextView Counter;

    private int firstint=0;
    private int secondint=0;
    private int levelpoints=-1;
    private int totalscore=-1;
    private int leftnumberint;
    private int rightnumberint;

    private int Countdownstart=3;

    private String PlayerName;


    private void ReadArguments()
    {
        PlayerName = getArguments().getString("player_name_file" , null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ReadArguments();

        return inflater.inflate(R.layout.fragment_game,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        introduction(view);

        startConter();

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

    private void introduction(View view)
    {
        score=(TextView) view.findViewById(R.id.game_score);
        level=(TextView) view.findViewById(R.id.game_level);
        timeremainig=(TextView) view.findViewById(R.id.Time_Remaining);
        leftbutton=(Button) view.findViewById(R.id.Left_button);
        rightbutton=(Button) view.findViewById(R.id.Right_button);
        equalbutton=(Button) view.findViewById(R.id.equal_button);
        output=(TextView) view.findViewById(R.id.out_put);
        buttonout=(Button) view.findViewById(R.id.button_out);
        Counter=(TextView) view.findViewById(R.id.counter);
        GameContainer=(LinearLayout) view.findViewById(R.id.game_container);
        }

        private void startConter()
        {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(Counter ,"scaleX" , 1f , 7f);
            scaleX.setDuration(1000);
            scaleX.start();
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(Counter , "scaleY" , 1f , 7f);
            scaleY.setDuration(1000);
            scaleY.start();
            ObjectAnimator Alphax = ObjectAnimator.ofFloat(Counter , "alpha" , 1,0);
            Alphax.setDuration(1000);

            Alphax.start();

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(scaleX , scaleY , Alphax);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    if(Countdownstart==0){GameContainer.setVisibility(getView().VISIBLE);}

                    else startConter();



                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    Counter.setText(String.valueOf(Countdownstart));
                    Countdownstart--;

                }
            });
            animatorSet.start();
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
                updateHighscore();
                output(5);
                output(6);
                output(7);
                output(8);

            }
        });
    }
    private void Timing()
    {
        CountDownTimer mycount=new CountDownTimer(10000,1000) {
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
                updateHighscore();

            }


        };
        mycount.start();


    }
    private void updateHighscore()
    {
        Ranklist ranklist = MyPreferenceManager.getInstance(getActivity()).getRanklist();
        User NewUser= new User();
        NewUser.setName(PlayerName);
        NewUser.setScore(totalscore);
        ranklist.addUserToList(NewUser);
        MyPreferenceManager.getInstance(getActivity()).putRanklist(ranklist);

//        User privioususer = MyPreferenceManager.getInstance(getActivity()).getBestUser();
//        if(privioususer == null || privioususer.getScore()<totalscore )
//        {
//            User NewUser= new User();
//            NewUser.setName(PlayerName);
//            NewUser.setScore(totalscore);
//            MyPreferenceManager.getInstance(getActivity()).putBestUser(NewUser);
//        }
    }

}
