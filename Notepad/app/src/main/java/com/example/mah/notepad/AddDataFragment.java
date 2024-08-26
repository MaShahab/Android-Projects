package com.example.mah.notepad;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class AddDataFragment extends Fragment {

    private EditText title;
    private EditText content;

    private MyDatabase myDatabase;

    private Button register;

    private ImageView voiceSearchImg;

    private final int Request_code = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_add_data , container ,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        introdcution(view);

        ActivityCompat.requestPermissions(getActivity (),new String[]{Manifest.permission.RECORD_AUDIO , Manifest.permission.WRITE_EXTERNAL_STORAGE},Request_code);

        voiceSearchImg.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                askSpeechInput ();

            }
        } );

        addData ();

    }

    private void introdcution(View view)
    {
        title = (EditText) view.findViewById ( R.id.edit_title );
        content = (EditText) view.findViewById ( R.id.id_content );
        register = (Button) view.findViewById ( R.id.btn_reg );
        voiceSearchImg = (ImageView) view.findViewById(R.id.voice_search_id);
    }

    private void addData()
    {
        register.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                MyPreferenceManager.getInstance ( getActivity () ).putTitle ( title.getText ().toString () );

                myDatabase = new MyDatabase ( getActivity () );

                Date date = Calendar.getInstance ().getTime ();
                SimpleDateFormat df = new SimpleDateFormat ( "dd-MMM-yyyy" );
//                Toast.makeText ( getActivity (), ""+df.format ( date ), Toast.LENGTH_SHORT ).show ();
                String currentDate = df.format ( date );

                Date date1 = new Date ();
                SimpleDateFormat sdf = new SimpleDateFormat ( "hh:mm a" );
//                Toast.makeText ( getActivity (), ""+currentDateTimeString, Toast.LENGTH_SHORT ).show ();
                String currentTime = sdf.format ( date1 );
                if (content.length () != 0 || title.length () != 0) {
                    boolean isInsert = myDatabase.insertData ( title.getText ().toString (), content.getText ().toString (), currentDate, currentTime );
                    if (isInsert == true) {
                        Toast.makeText ( getActivity (), "اطلاعات با موفقیت ثبت گردید", Toast.LENGTH_SHORT ).show ();
//                    DataListFragment dataListFragment = new DataListFragment ();
//                    getFragmentManager ().beginTransaction ()
//                            .add ( R.id.frame_container,dataListFragment )
//                            .commit ();
                        startActivity ( new Intent ( getActivity (), MainActivity.class ) );
                    } else {
                        Toast.makeText ( getActivity (), "متاسفانه ثبت اطلاعات با خطا روبرو شد !", Toast.LENGTH_LONG ).show ();
                    }

                }
                Toast.makeText ( getActivity (), "برای درج مطلب ، هر دو فیلد باید کامل باشد", Toast.LENGTH_LONG ).show ();
            }
        } );
    }

    private void askSpeechInput()
    {
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault ());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"متن مورد نظر خود را بگویید");
        try {
            startActivityForResult(intent,Request_code);
        }
        catch (ActivityNotFoundException a) { }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        switch (requestCode) {
            case Request_code: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    content.setText(result.get(0));
                }
                break;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );

        switch (requestCode)
        {
            case Request_code:
            {
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    askSpeechInput ();
                }
                else
                {
                    Toast.makeText ( getActivity (), "شما دسترسی به ظبط صدا را نداده اید", Toast.LENGTH_SHORT ).show ();
                }
            }
        }

    }
}
