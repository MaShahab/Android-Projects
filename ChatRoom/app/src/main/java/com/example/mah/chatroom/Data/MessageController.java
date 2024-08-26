
package com.example.mah.chatroom.Data;

import com.example.mah.chatroom.models.Messages;
import com.example.mah.chatroom.models.MessagesResponse;
import com.example.mah.chatroom.models.RoomId;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageController {
    ChatRoomAPI.GetMessagesCallback getMessagesCallback;

    public MessageController(ChatRoomAPI.GetMessagesCallback getMessagesCallback) {
        this.getMessagesCallback = getMessagesCallback;
    }
    public void start(String authorization , RoomId roomId)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call <MessagesResponse> call = chatRoomAPI.getMessage(authorization , roomId);
        call.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                if(response.isSuccessful())
                {
                    getMessagesCallback.onResponse(response.body ().getMessages ());
                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                getMessagesCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
