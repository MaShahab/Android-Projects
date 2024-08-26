package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUserController {
    ShopsAPIS.Loginusercallback loginusercallback;

    public LoginUserController(ShopsAPIS.Loginusercallback loginusercallback) {
        this.loginusercallback = loginusercallback;
    }

    public void start(String username,String password)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( ShopsAPIS.Base_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create(ShopsAPIS.class);
        Call<TokenResponse> call = shopsAPIS.loginUser(username ,password);
        call.enqueue ( new Callback <TokenResponse> () {
            @Override
            public void onResponse(Call <TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful ())
                {
                    loginusercallback.onResponse(true,null,response.body ());
                }
            }

            @Override
            public void onFailure(Call <TokenResponse> call, Throwable t) {
                loginusercallback.onFailure (t.getCause ().getMessage ());
            }
        } );
    }
}
