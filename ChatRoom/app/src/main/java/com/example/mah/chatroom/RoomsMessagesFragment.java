package com.example.mah.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mah.chatroom.Data.ChatRoomAPI;
import com.example.mah.chatroom.Data.MessageController;
import com.example.mah.chatroom.Data.RoomsController;
import com.example.mah.chatroom.Data.SendMessageController;
import com.example.mah.chatroom.models.Messages;
import com.example.mah.chatroom.models.MessagesResponse;
import com.example.mah.chatroom.models.Room;
import com.example.mah.chatroom.models.RoomId;
import com.example.mah.chatroom.models.Sending;

import java.util.ArrayList;
import java.util.List;

public class RoomsMessagesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessagesAdapter messagesAdapter;
    private EditText messageEdit;
    private Button sendButton;
    private EditText myMessage;
    private MessagesResponse messagesResponse = new MessagesResponse ();


        private List<Messages> messages = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms_messages , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        showmessages ();
        introduction(view);
//        sendingFunction();
        //ConfigureList();

    }
    private void introduction(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.messages_LIST);
        messageEdit = (EditText) view.findViewById(R.id.messsage_edit);
        sendButton = (Button) view.findViewById(R.id.button_send);
    }
    private void showmessages()
    {
        MessageController messageController = new MessageController(messagesCallback);
        Messages messages= new Messages ();
        RoomId roomId = new RoomId ();
        Bundle bundle = getArguments ();
        roomId.setRoomId (bundle.getString ( "id" ));
        messageController.start("bearer" + MyprefrenceManager.getInstance(getActivity()).getAccessToken() , roomId);
    }
    private void ConfigureList(List<Messages> messages)
    {
        messagesAdapter = new MessagesAdapter(messages);
        recyclerView.setAdapter(messagesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.scrollToPosition (messages.size()-1);
    }

    private void configViews()
    {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Messages messages = new Messages ();
                messages.setMyText(messageEdit.getText ().toString ());
                SendMessageController sendMessageController = new SendMessageController(sendMessageCallback);
                Sending sending = new Sending();
                sending.setText ( messageEdit.getText ().toString () );
                sending.setText(messageEdit.getText().toString());
                Bundle bundle = getArguments ();
                sending.setRoomId (bundle.getString ( "id" ));
                sending.setUsername(MyprefrenceManager.getInstance ( getActivity ()).getUSername ());
                sendMessageController.start("bearer" + MyprefrenceManager.getInstance(getActivity()).getAccessToken() , sending);
                messages.setMyText(messageEdit.getText ().toString ());
                Log.d("Tag" , MyprefrenceManager.getInstance(getActivity()).getAccessToken());
                sending.setText(messageEdit.getText ().toString ());
                Log.d("Tag" , "showoutput" +sending.getText());
                showmessages ();

            }
        });
    }


    private ChatRoomAPI.GetMessagesCallback messagesCallback = new ChatRoomAPI.GetMessagesCallback() {
        @Override
        public void onResponse(List<Messages> messagesList) {
            Log.d("Tag" , "messageslist" +messagesList.size());
            Messages messages = new Messages ();
            Room room = new Room ();
            messages.setId (room.getId ());
            configViews();
            ConfigureList(messagesList);
        }

        @Override
        public void onFailure(String cause) {

        }
    };
    private ChatRoomAPI.sendMessageCallback sendMessageCallback = new ChatRoomAPI.sendMessageCallback() {
        @Override
        public void onResponse(Sending sending) {

            configViews ();
            Log.d("Tag" , "sending" + sending.getRoomId());
        }

        @Override
        public void onFailure(String cause) {

        }
    };
}