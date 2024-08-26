package com.example.mah.whichoneislarger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button StartGameButton;
    public Button bestScoreButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartGameButton=(Button) findViewById(R.id.my_button);
        bestScoreButton=(Button) findViewById(R.id.best_score_button);



        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameDialog gameDialog = new GameDialog(MainActivity.this, new OnnameSelectedListener() {
                    @Override
                    public void OnNameSelected(String PlayerName) {

                        GameFragment gameFragment = new GameFragment();

                        Bundle bundle = new Bundle();
                        bundle.putString("player_name_file" , PlayerName);
                        gameFragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.my_frame,gameFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                });
                gameDialog.show();



            }
        });

        bestScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BestScoreFragment bestScoreFragment=new BestScoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.my_frame,bestScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });





    }



}
