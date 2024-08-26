package com.example.mah.jsonapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        introduction();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetNameDialog getNameDialog = new GetNameDialog(MainActivity.this, new OnNameSelectedListener() {
                    @Override
                    public void onNameSelected(String PlayerName) {

                        Log.d("Tag" , "PlayerName " + PlayerName);
                    }
                });
                getNameDialog.show();
            }
        });







    }
    private void introduction()
    {
        button=(Button) findViewById(R.id.my_button);
        editText=(EditText) findViewById(R.id.my_edit);

    }
}
