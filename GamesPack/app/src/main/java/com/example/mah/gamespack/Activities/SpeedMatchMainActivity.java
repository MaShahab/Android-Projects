package com.example.mah.gamespack.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mah.gamespack.OnNameSelctedListener;
import com.example.mah.gamespack.R;
import com.example.mah.gamespack.fragments.SpeedMatchBestScore;
import com.example.mah.gamespack.dialogs.SpeedMatchGameDialog;
import com.example.mah.gamespack.fragments.SpeedMatchGameFragment;

public class SpeedMatchMainActivity extends AppCompatActivity {

    private Button startGameButton;
    private Button bestScoreButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.speed_match_main);

        if(getSupportActionBar ()!=null)
        {
            getSupportActionBar ().hide ();
        }


        inroduction();

        startgame();

        bestScore();

    }
    private void inroduction ()
    {
        startGameButton = (Button) findViewById(R.id.start_game_button);
        bestScoreButton = (Button) findViewById(R.id.best_score_button);
    }

    private void startgame() {

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeedMatchGameDialog gameDialog = new SpeedMatchGameDialog(SpeedMatchMainActivity.this , new OnNameSelctedListener () {
                    @Override
                    public void onNameSelected(String playerName) {


                        SpeedMatchGameFragment gameFragment = new SpeedMatchGameFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("player_name", playerName);
                        gameFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.game_frame , gameFragment)
                                        .addToBackStack(null)
                                        .commit();
                    }
                });

                gameDialog.show();

            }
        });
    }

    private void bestScore() {
        bestScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeedMatchBestScore bestScoreFragment = new SpeedMatchBestScore();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.game_frame, bestScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
