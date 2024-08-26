package com.example.mah.chatroom.Data;

import com.example.mah.chatroom.models.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogUserController {
    ChatRoomAPI.LoginUserCallback loginUserCallback;

    public LogUserController(ChatRoomAPI.LoginUserCallback loginUserCallback) {
        this.loginUserCallback = loginUserCallback;
    }

    public void start(String userName , String Password)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<TokenResponse> call = chatRoomAPI.loginUser(userName ,Password);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful())
                {
                    loginUserCallback.onResponse(true , null , response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
    }

}
