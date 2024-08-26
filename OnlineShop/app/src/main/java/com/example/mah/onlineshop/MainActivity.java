package com.example.mah.onlineshop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mah.onlineshop.fragments.LoginFragment;
import com.example.mah.onlineshop.fragments.ProductsFragment;
import com.example.mah.onlineshop.fragments.RegisterFragment;
import com.example.mah.onlineshop.otherClasses.MyFragmnetPagerAdapter;
import com.example.mah.onlineshop.otherClasses.MypreferenceManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MyFragmnetPagerAdapter myFragmnetPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main);

        introduction ();

        if(getSupportActionBar ()!=null)
        {
            getSupportActionBar ().hide ();
        }

        if(MypreferenceManager.getInstance (MainActivity.this).getAccessToken () == null)
        {
            openRegisterfragment ();
        }
        else {
            openProductsFrag ();
        }
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver () {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager ().popBackStack ();
            openProductsFrag2();
        }
    };

    @Override
    protected void onResume() {
        super.onResume ();
        LocalBroadcastManager.getInstance ( MainActivity.this ).registerReceiver ( broadcastReceiver,new IntentFilter ( "open_products" ) );
    }

    @Override
    protected void onPause() {
        super.onPause ();
        LocalBroadcastManager.getInstance ( MainActivity.this ).unregisterReceiver ( broadcastReceiver );
    }


    private void openRegisterfragment()
    {
        RegisterFragment registerFragment = new RegisterFragment ();
        getSupportFragmentManager ().beginTransaction ()
                .add ( R.id.frame_container,registerFragment )
                .commit ();
    }

    private void openProductsFrag()
    {
        myFragmnetPagerAdapter=new MyFragmnetPagerAdapter(getSupportFragmentManager ());
        viewPager.setAdapter(myFragmnetPagerAdapter);
        viewPager.setCurrentItem(1);

//        ProductsFragment productsFragment = new ProductsFragment ();
//        getSupportFragmentManager ().beginTransaction ()
//                .add ( R.id.frame_container,productsFragment )
//                .commit ();

    }

    private void openProductsFrag2()
    {

        ProductsFragment productsFragment = new ProductsFragment ();
        getSupportFragmentManager ().beginTransaction ()
                .add ( R.id.frame_container,productsFragment )
                .commit ();

    }

    private void introduction()
    {
        tabLayout=(TabLayout) findViewById ( R.id.tab_layout_sign_out );
        viewPager=(ViewPager) findViewById ( R.id.view_pager_me );
    }

}
