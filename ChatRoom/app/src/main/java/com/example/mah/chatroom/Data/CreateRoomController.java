package com.example.mah.chatroom.Data;

import android.view.View;
import android.widget.EditText;

import com.example.mah.chatroom.MyprefrenceManager;
import com.example.mah.chatroom.R;
import com.example.mah.chatroom.models.CreateNewRoom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateRoomController {
    ChatRoomAPI.CreateRoomCallback createRoomCallback;

    public CreateRoomController(ChatRoomAPI.CreateRoomCallback createRoomCallback) {
        this.createRoomCallback = createRoomCallback;
    }
    public void start(String authorization ,CreateNewRoom createNewRoom )
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call <CreateNewRoom> call = chatRoomAPI.createRoom (authorization, createNewRoom);
        call.enqueue(new Callback<CreateNewRoom>() {
            @Override
            public void onResponse(Call<CreateNewRoom> call, Response<CreateNewRoom> response) {
                createRoomCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<CreateNewRoom> call, Throwable t) {
                createRoomCallback.onFailure (t.getCause ().getMessage ());

            }
        });
    }
}
