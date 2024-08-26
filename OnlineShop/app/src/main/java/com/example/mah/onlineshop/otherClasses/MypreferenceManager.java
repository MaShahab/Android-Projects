package com.example.mah.onlineshop.otherClasses;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mah.onlineshop.MainActivity;
import com.example.mah.onlineshop.models.TokenResponse;

public class MypreferenceManager {
    private static MypreferenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public static MypreferenceManager getInstance (Context context)
    {
        if(instance == null)
        {
            return new MypreferenceManager (context);
        }
        return instance;
    }

    private MypreferenceManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences("file",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit ();
    }

    public void putUsername(String username)
    {
        editor.putString("username" , username);
        editor.apply ();
    }
    public String getUsername()
    {
        return sharedPreferences.getString ("username",null);
    }

    public void putAccesstoken(String accessToken)
    {
        editor.putString("access_token" , accessToken);
        editor.apply ();
    }
    public String getAccessToken()
    {
        return sharedPreferences.getString("access_token",null);
    }

    public void putdescription(String describe)
    {
        editor.putString("description" , describe);
        editor.apply ();
    }
    public String getdescription()
    {
        return sharedPreferences.getString ("description",null);
    }
    public void putID(String id)
    {
        editor.putString ("productId" , id);
        editor.apply ();
    }
    public String getID()
    {
        return sharedPreferences.getString("productId" , null);
    }
    public void putUsernameOrder(String orderUsername)
    {
        editor.putString("orderusername", orderUsername);
        editor.apply ();
    }
    public String getUsernameOrder()
    {
        return sharedPreferences.getString("orderusername",null);
    }
    public void putProductId(String productId)
    {
        editor.putString("productId" , productId);
        editor.apply ();
    }
    public String getProductId()
    {
        return sharedPreferences.getString("productId", null);
    }
    public void putAddressId(String addressId)
    {
        editor.putString("addressId" , addressId);
        editor.apply ();
    }
    public String getAddressId()
    {
        return sharedPreferences.getString ( "addressId", null );
    }

    public void putUsernameSignOut(String username)
    {
        editor.putString("sign_out_username",username);
        editor.apply ();
    }
    public String getUsernameSignOut()
    {
        return sharedPreferences.getString ( "sign_out_username",null );
    }
    public void putPassSignOut(String passSignout)
    {
        editor.putString("pass_sign_out" , passSignout);
        editor.apply ();
    }
    public String getPassSignOut()
    {
        return sharedPreferences.getString("pass_sign_out",null);
    }
    public void putEmailsignout(String emailSignout)
    {
        editor.putString("email_sign_out",emailSignout);
        editor.apply ();
    }
    public String getEmailSignOut()
    {
        return sharedPreferences.getString("email_sign_out",null);
    }
}
