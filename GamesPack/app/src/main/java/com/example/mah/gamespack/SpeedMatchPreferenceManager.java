package com.example.mah.gamespack;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mah.gamespack.models.Ranklist;
import com.google.gson.Gson;

public class SpeedMatchPreferenceManager {
    private static SpeedMatchPreferenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public static SpeedMatchPreferenceManager getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new SpeedMatchPreferenceManager (context);
        }
        return  instance;
    }

    private SpeedMatchPreferenceManager (Context context)
    {
        sharedPreferences=context.getSharedPreferences("speedmatchpreference" , Context.MODE_PRIVATE);
        editor=sharedPreferences.edit ();
    }

    public void putRanklist(Ranklist ranklist)
    {
        Gson gson = new Gson ();
        String userjson = gson.toJson(ranklist,Ranklist.class);
        editor.putString("speedMatchRankList" , userjson);
        editor.apply ();
    }


    public Ranklist getRankList()
    {
        String userJason = sharedPreferences.getString("speedMatchRankList",null);
        if(userJason==null)
        {
            return new Ranklist ();
        }
        Gson gson = new Gson ();
        return gson.fromJson(userJason,Ranklist.class);
    }
}
