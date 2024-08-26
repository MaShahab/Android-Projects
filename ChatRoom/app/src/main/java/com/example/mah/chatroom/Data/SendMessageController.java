package com.example.mah.chatroom.Data;

import com.example.mah.chatroom.models.Messages;
import com.example.mah.chatroom.models.Sending;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendMessageController {
    private ChatRoomAPI.sendMessageCallback sendMessageCallback;

    public SendMessageController(ChatRoomAPI.sendMessageCallback sendMessageCallback) {
        this.sendMessageCallback = sendMessageCallback;
    }

    public void start( String authorization ,Sending sending )
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI = retrofit.create(ChatRoomAPI.class);
        Call<Sending> call = chatRoomAPI.sendMessage(authorization , sending);
        call.enqueue(new Callback<Sending>() {
            @Override
            public void onResponse(Call<Sending> call, Response<Sending> response) {
                if(response.isSuccessful())
                {
                    sendMessageCallback.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<Sending> call, Throwable t) {
                sendMessageCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
