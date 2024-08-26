package com.example.mah.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mah.chatroom.Data.ChatRoomAPI;
import com.example.mah.chatroom.Data.CreateRoomController;
import com.example.mah.chatroom.models.CreateNewRoom;

public class CreateRoomFragment extends Fragment {

    private EditText newRoomEdit;
    private Button submit;


    public CreateRoomFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_room , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        introdction(view);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRoomController createRoomController = new CreateRoomController(createRoomCallback);
                CreateNewRoom createNewRoom = new CreateNewRoom ();
                createNewRoom.setName (newRoomEdit.getText ().toString ());
                createRoomController.start("bearer" + MyprefrenceManager.getInstance(getActivity()).getAccessToken() ,createNewRoom);
                Toast.makeText ( getActivity (), "Your room created successfully ! now you can back on rooms list to observe that", Toast.LENGTH_LONG ).show ();
                new RoomsFragment ();
            }
        });


    }
    private void introdction(View view)
    {
        newRoomEdit = (EditText) view.findViewById(R.id.edittext_room_name);
        submit = (Button) view.findViewById(R.id.create_room_button);
    }
    ChatRoomAPI.CreateRoomCallback createRoomCallback = new ChatRoomAPI.CreateRoomCallback() {
        @Override
        public void onResponse(CreateNewRoom createNewRoom) {
            Log.d("Tag" , "onResponse" +createNewRoom);
        }

        @Override
        public void onFailure(String cause) {

        }
    };
}
