package com.example.mah.notepad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        if(getSupportActionBar () !=null)
        {
            getSupportActionBar ().hide ();
        }

        openNotesList ();

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver () {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager ().popBackStack ();
            openNotesList ();
        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume ();
        LocalBroadcastManager.getInstance ( MainActivity.this ).registerReceiver ( broadcastReceiver , new IntentFilter ( "open_notes_page" ) );
    }

    @Override
    protected void onPause() {
        super.onPause ();
        LocalBroadcastManager.getInstance ( MainActivity.this ).unregisterReceiver ( broadcastReceiver );
    }

    private void openNotesList()
    {
        DataListFragment dataListFragment = new DataListFragment ();
        getSupportFragmentManager ().beginTransaction ()
                .add ( R.id.frame_container, dataListFragment )
                .commit ();
    }
}
