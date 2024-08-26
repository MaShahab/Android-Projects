package com.example.mah.backstack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TopFragment mytop=new TopFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.first_fragment,mytop)
                .commit();
        BottomFragment mybottom=new BottomFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.second_fragment,mybottom)
                .commit();



    }
}
