package com.example.mah.chatroom.Data;

import com.example.mah.chatroom.models.ErrorResponse;
import com.example.mah.chatroom.models.User;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUSerController {

    ChatRoomAPI.RegisterUserCallback registerUserCallback;

    public RegisterUSerController(ChatRoomAPI.RegisterUserCallback registerUserCallback) {
        this.registerUserCallback = registerUserCallback;
    }

    public void start(final User user)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<User> call = chatRoomAPI.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                {
                    registerUserCallback.onResponse(true , null , response.body());
                }
                else {
                    try {
                        String errorBodyJson = response.errorBody().string();
                        Gson gson = new Gson();
                        ErrorResponse errorResponse = gson.fromJson(errorBodyJson,ErrorResponse.class);
                        registerUserCallback.onResponse(false , errorResponse.getMessage() , null);
                    }
                    catch (IOException e){

                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                registerUserCallback.onfailure(t.getCause().getMessage());
            }
        });
    }
}
