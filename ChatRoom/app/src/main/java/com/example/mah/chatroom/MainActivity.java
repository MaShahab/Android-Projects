package com.example.mah.chatroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyfragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        introduction();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (MyprefrenceManager.getInstance(MainActivity.this).getAccessToken() == null) {
            registerFragment();
        } else {
            otherFragment();
        }
    }

    private void introduction()
    {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    private void registerFragment()
    {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container , registerFragment)
                .addToBackStack(null)
                .commit();
    }
    private void otherFragment()
    {
        fragmentPagerAdapter = new MyfragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
//        RoomsFragment roomsFragment = new RoomsFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.frame_container,roomsFragment)
//                .commit();
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager().popBackStack();
            otherFragment();
        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(broadcastReceiver,new IntentFilter("login_ok"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(broadcastReceiver);
    }
}
