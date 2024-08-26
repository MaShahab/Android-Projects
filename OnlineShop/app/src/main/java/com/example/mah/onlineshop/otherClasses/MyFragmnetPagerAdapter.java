package com.example.mah.onlineshop.otherClasses;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mah.onlineshop.fragments.ProductsFragment;
import com.example.mah.onlineshop.fragments.signOutFragmnet;

public class MyFragmnetPagerAdapter extends FragmentPagerAdapter {
    public MyFragmnetPagerAdapter(FragmentManager fm) {
        super ( fm );
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            return new signOutFragmnet ();
        }
        else {
            return new ProductsFragment ();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
