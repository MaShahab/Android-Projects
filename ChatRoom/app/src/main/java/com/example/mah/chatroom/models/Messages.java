package com.example.mah.chatroom.models;

import android.widget.Button;

import com.google.gson.annotations.SerializedName;

public class Messages {
    @SerializedName("_id")
    private String id;
    private String roomId;
    private String text;
    private String username;
    private String myText;

    public Messages() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }
}
