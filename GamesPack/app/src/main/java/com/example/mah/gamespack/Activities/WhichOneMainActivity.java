package com.example.mah.gamespack.Activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mah.gamespack.OnNameSelctedListener;
import com.example.mah.gamespack.R;
import com.example.mah.gamespack.fragments.WhichOneBestScore;
import com.example.mah.gamespack.dialogs.WhichOneGameDialog;
import com.example.mah.gamespack.fragments.WhichOneGameFragment;

public class WhichOneMainActivity extends AppCompatActivity
{
    public Button StartGameButton;
    public Button bestScoreButton;

//    public MediaPlayer mediaPlayer1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.which_one_main);

//        mediaPlayer1 = MediaPlayer.create ( WhichOneMainActivity.this, R.raw.highway);
//        mediaPlayer1.seekTo(6000);
//        mediaPlayer1.setLooping ( true );
//        mediaPlayer1.setVolume ( 100, 100 );
//        mediaPlayer1.start ();

        if(getSupportActionBar ()!=null)
        {
            getSupportActionBar ().hide ();
        }

        StartGameButton=(Button) findViewById(R.id.my_button);
        bestScoreButton=(Button) findViewById(R.id.best_score_button);


        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WhichOneGameDialog gameDialog = new WhichOneGameDialog(WhichOneMainActivity.this, new OnNameSelctedListener () {
                    @Override
                    public void onNameSelected(String PlayerName) {
                        Log.d("Tag" , "PlayerName "  +PlayerName);

                        WhichOneGameFragment gameFragment = new WhichOneGameFragment();

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
                WhichOneBestScore bestScoreFragment=new WhichOneBestScore();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.my_frame,bestScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });




    }

//    @Override
//    public void onPause() {
//        super.onPause ();
//        mediaPlayer1.stop();
//        mediaPlayer1.release();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume ();
//        mediaPlayer1 = MediaPlayer.create(WhichOneMainActivity.this, R.raw.highway);
//        mediaPlayer1.setLooping ( true );
//        mediaPlayer1.seekTo(6000);
//        mediaPlayer1.setVolume ( 100, 100 );
//        mediaPlayer1.start ();
//    }

}
