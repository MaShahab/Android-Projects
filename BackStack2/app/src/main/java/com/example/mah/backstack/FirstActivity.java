package com.example.mah.backstack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    private TextView MyText;
    private Button MyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.d("Tag","oncreate started");

        MyText=(TextView) findViewById(R.id.my_text);
        MyButton=(Button) findViewById(R.id.my_button);

        MyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FirstActivity.this,SecondActivity.class));

            }
        });



    }

    @Override
    protected void onPostResume() {

        super.onPostResume();

        Log.d("Tag","onresume started");
        MyText.setText("onpost resume");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Tag","onpause started");
        MyText.setText("onpause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag","onresume started");
        MyText.setText("Onresume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Tag","ondestroy started");
        MyText.setText("onDestroy");
    }
}
