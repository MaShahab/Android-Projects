package com.example.mah.jsonapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MyPreferenceManager 
{
    private static MyPreferenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;


    public static MyPreferenceManager getInstance (Context context)
    {
        if(instance == null)
        {
            instance = new MyPreferenceManager(context);
        }
        return instance;
    }
    private MyPreferenceManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences("PRF" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public int getHihscore()
    {
        return sharedPreferences.getInt("File" , 0);
    }
    public void putHighscore(int highscore)
    {
        editor.putInt("File" , highscore);
        editor.apply();
    }

    public User getBestscore()
    {
        String UserJson = sharedPreferences.getString("best_score_file" , null);
        if(UserJson == null) {return null;}
        Gson gson = new Gson();
        return gson.fromJson(UserJson , User.class);
    }

    public void putBestscore(String BestScore)
    {
        Gson gson = new Gson();
        String UserJson = gson.toJson(BestScore , User.class);
        editor.putString("best_score_file" , UserJson);
        editor.apply();
    }

}