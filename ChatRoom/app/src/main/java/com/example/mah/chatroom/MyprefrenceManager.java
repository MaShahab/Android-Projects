package com.example.mah.chatroom;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mah.chatroom.models.TokenResponse;

public class MyprefrenceManager {
    private static MyprefrenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor =null;

    public static MyprefrenceManager getInstance(Context context)
    {
        if(instance==null)
        {
            return new MyprefrenceManager(context);
        }
        return instance;
    }

    private MyprefrenceManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences("Mypref_file" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void putUsername(String userName)
    {
        editor.putString("username" ,userName);
        editor.apply();
    }
    public String getUSername()
    {
        return sharedPreferences.getString("username" , null);
    }
    public void putAccessToken(String accessToken)
    {
        editor.putString("access_token" , accessToken);
        editor.apply();
    }
    public String getAccessToken()
    {
        return sharedPreferences.getString("access_token" ,null);
    }
}
