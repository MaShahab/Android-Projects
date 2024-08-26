package com.example.mah.onlineshop.Data;

import com.example.mah.onlineshop.models.ErrorResponse;
import com.example.mah.onlineshop.models.User;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUserController {
    private ShopsAPIS.RegisterUserCallback registerUserCallback;

    public RegisterUserController(ShopsAPIS.RegisterUserCallback registerUserCallback) {
        this.registerUserCallback = registerUserCallback;
    }

    public void start(User user)
    {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl (ShopsAPIS.Base_URL)
                .addConverterFactory ( GsonConverterFactory.create ())
                .build ();
        ShopsAPIS shopsAPIS = retrofit.create ( ShopsAPIS.class );
        Call<User> call = shopsAPIS.registerUser ( user );
        call.enqueue ( new Callback <User> () {
            @Override
            public void onResponse(Call <User> call, Response<User> response) {
                if(response.isSuccessful ())
                {
                    registerUserCallback.onResponse(true,null,response.body ());
                }
                else {
                    try {
                        String errorBodyJson = response.errorBody().string();
                        Gson gson = new Gson ();
                        ErrorResponse errorResponse = gson.fromJson(errorBodyJson,ErrorResponse.class);
                        registerUserCallback.onResponse(false,errorResponse.getMessage (),null);
                    }
                    catch (IOException e){

                    }
                }
            }

            @Override
            public void onFailure(Call <User> call, Throwable t) {
                registerUserCallback.onFailure ( t.getCause ().getMessage () );

            }
        } );
    }
}
