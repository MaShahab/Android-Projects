package com.example.mah.gamespack.Activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mah.gamespack.R;

public class MainActivity extends AppCompatActivity {

    private Button speedMatchButton;
    private Button whichOneLargerButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        mediaPlayer = MediaPlayer.create ( MainActivity.this, R.raw.high_hill );
        mediaPlayer.seekTo(18000);
        mediaPlayer.setLooping ( true );
        mediaPlayer.setVolume ( 100, 100 );
        mediaPlayer.start ();

        if(getSupportActionBar ()!=null)
        {
            getSupportActionBar ().hide ();
        }

        introduction();

        Toast.makeText ( MainActivity.this, "developed by mahdi shahabedin", Toast.LENGTH_LONG ).show ();

        onClickGames();
    }

    private void introduction()
    {
        speedMatchButton=(Button) findViewById(R.id.speed);
        whichOneLargerButton=(Button) findViewById(R.id.which_one);
    }
    private void onClickGames()
    {
        speedMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpeedMatchMainActivity.class));

                    }
                });

        whichOneLargerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , WhichOneMainActivity.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause ();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume ();
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.high_hill);
        mediaPlayer.setLooping ( true );
        mediaPlayer.seekTo(18000);
        mediaPlayer.setVolume ( 100, 100 );
        mediaPlayer.start ();
    }
}
