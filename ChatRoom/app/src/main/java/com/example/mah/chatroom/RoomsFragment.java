package com.example.mah.chatroom;

import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mah.chatroom.Data.ChatRoomAPI;
import com.example.mah.chatroom.Data.CreateRoomController;
import com.example.mah.chatroom.Data.RoomsController;
import com.example.mah.chatroom.models.CreateNewRoom;
import com.example.mah.chatroom.models.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomsFragment extends Fragment {
    private RecyclerView rooms;
    private List<Room> roomList = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView ProgressShowing;
    private Button createnewRomm;
    RoomAdapter roomAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room ,container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoomsController roomsController = new RoomsController(roomsCallback);
        roomsController.start("bearer" + MyprefrenceManager.getInstance(getActivity()).getAccessToken());

        findviews(view);
        initRoomList();
        newRoomOnclick();
        progressBar.setVisibility(View.VISIBLE);


    }

    private void newRoomOnclick()
    {
        createnewRomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRoomFragment createRoomFragment = new CreateRoomFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.frame_container , createRoomFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    private void findviews(View view)
    {
        rooms = (RecyclerView) view.findViewById(R.id.rooms);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        ProgressShowing = (TextView) view.findViewById(R.id.progress_showing);
        createnewRomm = (Button) view.findViewById(R.id.new_room_button);
    }
    private void initRoomList()
    {
        roomAdapter = new RoomAdapter(roomList);
        rooms.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rooms.setAdapter(roomAdapter);
    }
    private ChatRoomAPI.GetRoomsCallback roomsCallback = new ChatRoomAPI.GetRoomsCallback() {
        @Override
        public void onResponse(List<Room> inputList) {
            Log.d("Tag" , "roomList" +roomList.size());
            new SortRoomAsync(inputList).execute();


        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private ChatRoomAPI.CreateRoomCallback createRoomCallback = new ChatRoomAPI.CreateRoomCallback() {
        @Override
        public void onResponse(CreateNewRoom createNewRoom) {

        }

        @Override
        public void onFailure(String cause) {

        }
    };

    private class SortRoomAsync extends AsyncTask<Void ,Integer, Void>
    {
        List<Room> roomsList;

        public SortRoomAsync(List<Room> roomList) {
            this.roomsList = roomList;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0 ; i<10000; i++)
            {
                Log.d("Tag" , "back" +i/10000);
                publishProgress(i*100/10000);
                if(i>=9999){
                ProgressShowing.setVisibility(View.INVISIBLE);}

                Collections.sort(roomList, new Comparator<Room>() {
                    @Override
                    public int compare(Room x, Room y) {
                        return x.getName().compareTo(y.getName());
                    }
                });
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ProgressShowing.setText("Progress " + String.valueOf(values[0])+" / 100");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressBar.isShown())
            {
                progressBar.setVisibility(View.INVISIBLE);
            }
            roomList.clear();
            roomList.addAll(this.roomsList);
            roomAdapter.notifyDataSetChanged();
        }
    }
}

