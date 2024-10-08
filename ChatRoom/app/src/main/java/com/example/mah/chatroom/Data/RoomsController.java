package com.example.mah.chatroom.Data;

import com.example.mah.chatroom.models.RoomResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomsController {
    ChatRoomAPI.GetRoomsCallback roomsCallback;

    public RoomsController(ChatRoomAPI.GetRoomsCallback roomsCallback) {
        this.roomsCallback = roomsCallback;
    }

    public void start(String authorization)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<RoomResponse> call = chatRoomAPI.getRooms(authorization);
        call.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                if(response.isSuccessful())
                {
                    roomsCallback.onResponse(response.body().getRooms());
                }

            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {

            }
        });
    }
}
