package com.example.mah.notepad;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {

    private static MyPreferenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public static MyPreferenceManager getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new MyPreferenceManager (context);
        }
        return instance;
    }

    private MyPreferenceManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences("shared_file",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit ();
    }

    public void putTitle(String title)
    {
        editor.putString("title",title);
        editor.apply ();
    }
    public String getmyTitle()
    {
        return sharedPreferences.getString("title",null);
    }
}