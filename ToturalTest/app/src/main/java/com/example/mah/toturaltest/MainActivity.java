package com.example.mah.toturaltest;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.PowerManager;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button recordButton;
    private Button stopRecord;
    private TextView textView;
    private final int REQUEST_CODE = 20;
//    private final int REQUEST_CODE_record_voice = 30;
//    private MediaRecorder recorder;
//    private MediaPlayer myPlayer;
//    private Button playButtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

//        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.RECORD_AUDIO , Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_record_voice);

        button = (Button) findViewById(R.id.btn_record);
//        recordButton = (Button) findViewById(R.id.btn_voice_record );
        textView = (TextView) findViewById(R.id.txt );
//        stopRecord = (Button) findViewById(R.id.btn_voice_record_stop);
//        playButtn = (Button) findViewById ( R.id.btn_play );

        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                askSpeechInput();

            }
        } );

//        playButtn.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//
//                play(view);
//
//            }
//        } );



    }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra( RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException a) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                }
                break;
            }
        }
    }

//    public void startRecording() throws IOException
//    {
//        SimpleDateFormat timeStampFormat = new SimpleDateFormat (
//                "yyyy-MM-dd-HH.mm.ss");
//        String fileName = "try_audio" + timeStampFormat.format(new Date ())
//                + ".mp4";
//        recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        recorder.setOutputFile("/sdcard/"+fileName);
//        recorder.prepare();
//        recorder.start();
//    }
//    protected void stopRecording() {
//        recorder.stop();
//        recorder.release();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
//
//        switch (requestCode)
//        {
//            case REQUEST_CODE_record_voice:
//
//                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
//                {
//                    recordButton.setOnClickListener ( new View.OnClickListener () {
//                        @Override
//                        public void onClick(View view) {
//
//                            try {
//                                startRecording ();
//                            } catch (IOException e) {
//                                e.printStackTrace ();
//                            }
//
//                            stopRecord.setOnClickListener ( new View.OnClickListener () {
//                                @Override
//                                public void onClick(View view) {
//
//                                    stopRecording ();
//
//                                }
//                            } );
//
//                        }
//                    } );
//                }
//                else
//                {
//                    Toast.makeText ( this, "record permission needed to voice recorded", Toast.LENGTH_SHORT ).show ();
//                }
//        }
//
//    }
//    public void play(View view) {
//        try{
//            myPlayer = new MediaPlayer ();
//            myPlayer.setDataSource("try_audio");
//            myPlayer.prepare();
//            myPlayer.start();
//
//            Toast.makeText(getApplicationContext(), "Start play the recording...",
//                    Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
