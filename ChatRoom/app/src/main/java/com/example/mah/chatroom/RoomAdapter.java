package com.example.mah.chatroom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mah.chatroom.models.Messages;
import com.example.mah.chatroom.models.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {


    private List<Room> items;
    private RoomsMessagesFragment roomsMessagesFragment;


    public RoomAdapter(List<Room> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_room , viewGroup ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(items.get(i).getName());
        Room room = new Room ();
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Room room = new Room ();
                Messages messages = new Messages ();
                Bundle bundle = new Bundle ();
                bundle.putString ( "id" , items.get(i).getId());
                AppCompatActivity RoomsMessagesFragment = (AppCompatActivity) view.getContext();
                RoomsMessagesFragment roomsMessagesFragment = new RoomsMessagesFragment();
                roomsMessagesFragment.setArguments ( bundle );
                RoomsMessagesFragment.getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_container,roomsMessagesFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (Button) itemView.findViewById(R.id.name_button);
        }
    }
}
