package com.example.mah.gamespack;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mah.gamespack.models.Ranklist;
import com.google.gson.Gson;

public class MyPreferenceManager {

    private static MyPreferenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public static MyPreferenceManager getInstance(Context context)
    {
        if(instance==null){
            instance=new MyPreferenceManager(context);
        }
        return instance;
    }

    private MyPreferenceManager (Context context)
    {
        sharedPreferences = context.getSharedPreferences("file" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void putRanklist(Ranklist ranklist)
    {
        Gson gson = new Gson();
        String ranklistJson = gson.toJson(ranklist , Ranklist.class);
        editor.putString("rank_list_file" ,ranklistJson);
        editor.apply();
    }
    public Ranklist getRanklist()
    {
        String ranklisJson = sharedPreferences.getString("rank_list_file" , null);
        if(ranklisJson==null)
        {
            return new Ranklist();
        }
        Gson gson = new Gson();
        return gson.fromJson(ranklisJson,Ranklist.class);
    }

}
