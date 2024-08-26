package com.example.mah.whichoneislarger;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class MyPreferenceManager
{
    private static MyPreferenceManager instance=null;
    private SharedPreferences sharedPreferences=null;
    private SharedPreferences.Editor editor=null;

    public static MyPreferenceManager getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new MyPreferenceManager(context);
        }
        return instance;
    }

    private MyPreferenceManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences("PRF" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public int getHighscore()
    {
        return sharedPreferences.getInt("file" , 0);
    }
    public void putHighscore(int highscore)
    {
        editor.putInt("file" , highscore);
        editor.apply();
    }
    public String getEdit()
    {
        return sharedPreferences.getString("Edit_File" , null);
    }
    public void putEdit(String UserText)
    {
        editor.putString("Edit_File" , UserText );
    }

    public User getBestUser()
    {
        String UserJson = sharedPreferences.getString("best_user_file" , null);
        if(UserJson== null)
        {return null;}
        Gson gson = new Gson();
        return gson.fromJson(UserJson , User.class);
    }

    public void putBestUser(User BestUser)
    {
        Gson gson = new Gson();
        String UserJson = gson.toJson(BestUser , User.class);
        editor.putString("best_user_file" , UserJson);
        editor.apply();
    }

    public void putRanklist(Ranklist ranklist)
    {
        Gson gson = new Gson();
        String ranklistJson = gson.toJson(ranklist , Ranklist.class);
        editor.putString("rank_list_file" , ranklistJson);
        editor.apply();
    }

    public Ranklist getRanklist()
    {
        String RankListJson = sharedPreferences.getString("rank_list_file" , null);
        if (RankListJson==null) {return new Ranklist();}
        {
            Gson gson = new Gson();
            return gson.fromJson(RankListJson , Ranklist.class);
        }
    }

}
