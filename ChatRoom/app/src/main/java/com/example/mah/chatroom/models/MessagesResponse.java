package com.example.mah.chatroom.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessagesResponse {
    @SerializedName("results")
    List<Messages> messages;
    private String roomId;

    public MessagesResponse() {
    }

    public List <Messages> getMessages() {
        return messages;
    }

    public void setMessages(List <Messages> messages) {
        this.messages = messages;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
