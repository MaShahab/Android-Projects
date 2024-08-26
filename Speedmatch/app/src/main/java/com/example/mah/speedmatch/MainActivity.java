package com.example.mah.speedmatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;
    private Button bestScoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inroduction();

        startgame();

        bestScore();

    }
    private void inroduction()
    {
        startGameButton =(Button) findViewById(R.id.start_game_button);
        bestScoreButton =(Button) findViewById(R.id.best_score_button);
    }

    private void startgame()
    {

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameDialog gameDialog = new GameDialog(MainActivity.this, new OnNameSelctedListener() {
                    @Override
                    public void onNameSelected(String playerName) {

                        GameFragment gameFragment = new GameFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("player_name",playerName);
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

    private void bestScore()
    {
        bestScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BestScoreFragment bestScoreFragment = new BestScoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.game_frame ,bestScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
